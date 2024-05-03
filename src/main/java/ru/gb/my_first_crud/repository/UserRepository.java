package ru.gb.my_first_crud.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.my_first_crud.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable (firstName,lastName) VALUES ( ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return  user;
    }

    /**
     * Ищет пользователя по заданному идентификатору.
     *
     * @param id Идентификатор пользователя
     * @return Пользователь с заданным идентификатором или null, если не найден
     */
    public User findById(int id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        return jdbc.queryForObject(sql, getUserRowMapper(), id);
    }

    /**
     * Получает RowMapper для сопоставления данных из ResultSet с объектом User.
     *
     * @return RowMapper для User
     */
    private RowMapper<User> getUserRowMapper() {
        return (resultSet, i) -> mapUser(resultSet);
    }

    /**
     * Сопоставляет данные из ResultSet с объектом User.
     *
     * @param resultSet ResultSet с данными пользователя
     * @return Объект User с данными из ResultSet
     * @throws SQLException
     */
    private User mapUser(ResultSet resultSet) throws SQLException {
        User rowObject = new User();
        rowObject.setId(resultSet.getInt("id"));
        rowObject.setFirstName(resultSet.getString("firstName"));
        rowObject.setLastName(resultSet.getString("lastName"));
        return rowObject;
    }

    //public void deleteById(int id)
    //"DELETE FROM userTable WHERE id=?"

    public void deleteById(int id){
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    public User update(User user) {
        String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }
}
