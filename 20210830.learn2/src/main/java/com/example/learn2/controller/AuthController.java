package com.example.learn2.controller;

import com.example.learn2.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @PostMapping("/login")
    public String login(ModelMap modelMap, HttpSession session, @RequestParam @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelMap.put("errorMessage", buildErrorMessage(bindingResult));
            return "login";
        }

        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "index";
        }

        modelMap.put("errorMessage", "登陆失败，账号或密码错误");
        return "login";
    }

    private String buildErrorMessage(BindingResult bindingResult) {
        StringBuilder builder = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            builder.append(error.getDefaultMessage());
            builder.append(",");
        }

        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
