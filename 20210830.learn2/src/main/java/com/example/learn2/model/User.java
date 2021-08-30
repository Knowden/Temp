package com.example.learn2.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class User {

    @NotNull(message = "用户名不能为空")
    @Length(max = 30, message = "用户名最长为30个字符")
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(max = 30, message = "密码最长为30个字符")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
