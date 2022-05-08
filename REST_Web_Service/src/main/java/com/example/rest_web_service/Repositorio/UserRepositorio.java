package com.example.rest_web_service.Repositorio;

import com.example.rest_web_service.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepositorio {

    ArrayList<User> users = new ArrayList<>();

    public UserRepositorio(){
        users.add(new User(1,"pepitogrillo@hola.hola","hola","Pepito Grillo"));
        users.add(new User(2,"malhazar@hola.hola","hola","Malhazar LOL"));
        users.add(new User(3,"antpepe@hola.hola","hola","Antonio Pepinillo"));
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        for (User user: users) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        users.add(user);
    }

    public void removeUserById(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public void updateUser(User user, int id) {
        int i = 0;
        for (User u: users) {
            if (u.getId() == id){
                break;
            }
            i++;
        }
        users.set(i, user);
    }
}
