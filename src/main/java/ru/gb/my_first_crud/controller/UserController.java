package ru.gb.my_first_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.my_first_crud.model.User;
import ru.gb.my_first_crud.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    //@GetMapping("user-delete/{id}")

    /**
     * Обработчик HTTP GET-запроса для отображения формы удаления пользователя.
     *
     * @param id Идентификатор пользователя для удаления.
     * @return Перенаправление на страницу со списком пользователей после удаления.
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * Обработчик HTTP GET-запроса для отображения формы обновления данных пользователя.
     *
     * @param id    Идентификатор пользователя, чьи данные нужно обновить.
     * @param model Модель, предоставляющая данные для представления.
     * @return Название представления "user-update".
     */
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getOne(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    /**
     * Обработчик HTTP POST-запроса для обновления данных пользователя.
     *
     * @param user Данные пользователя для обновления.
     * @return Перенаправление на страницу со списком пользователей после обновления.
     */
    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}
