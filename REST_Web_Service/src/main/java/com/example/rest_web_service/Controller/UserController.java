package com.example.rest_web_service.Controller;

import com.example.rest_web_service.Model.User;
import com.example.rest_web_service.Model.UserDao;
import com.example.rest_web_service.Model.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    UserDao userDao;

    public List<User> getAllUser(){
        return userDao.findAll();
    }

    @Autowired
    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    public User findUserById(int id){
        return userDao.findById(id).get();
    }

    public void addUser(UserDto userDto) {
        User newUser = new User(userDto.getId(), userDto.getEmail(), userDto.getPassword(), userDto.getFullName());
        userDao.save(newUser);
    }

    public void removeUserById(int id) {
        userDao.deleteById(id);
    }

    public void updateUser(UserDto userDto, int id) {
        User u = new User(userDto.getId(), userDto.getEmail(), userDto.getPassword(), userDto.getFullName());
        User aCambiar = findUserById(id);

        //El id no se pone ya que se autogenera y dara problemas
        aCambiar.setEmail(u.getEmail());
        aCambiar.setFullName(u.getFullName());
        aCambiar.setPassword(u.getPassword());

        userDao.save(u);
    }

    public void applyPatchToUser(JsonPatch patch, int id) throws JsonPatchException, JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Optional<User> targetUser = userDao.findById(id);
        JsonNode patched = patch.apply(om.convertValue(targetUser.get(), JsonNode.class));
        User userACambiar = om.treeToValue(patched, User.class);
        userDao.save(userACambiar);
    }
}
