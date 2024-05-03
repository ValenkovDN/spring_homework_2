package ru.gb.my_first_crud.service;

import org.springframework.stereotype.Service;
import ru.gb.my_first_crud.model.User;
import ru.gb.my_first_crud.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    //public void deleteById(int id)

    /**
     * Метод для удаления пользователя из базы данных по его идентификатору.
     * @param id Идентификатор пользователя для удаления
     */
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    /**
     * Метод для получения пользователя по его идентификатору.
     * @param id Идентификатор пользователя
     * @return Найденный пользователь
     */
    public User getOne(int id) {
        return userRepository.findById(id);
    }

    /**
     * Метод для обновления данных пользователя в базе данных.
     * @param user Обновляемый пользователь
     * @return Обновленный пользователь
     */
    public User updateUser(User user) {
        return userRepository.update(user);
    }
}
