package com.example.rest_web_service.Resource;

import com.example.rest_web_service.Controller.UserController;
import com.example.rest_web_service.Model.User;
import com.example.rest_web_service.Model.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UserResource.USER_RESOURCE)
public class UserResource {
    public final static String USER_RESOURCE = "/users";
    UserController userController;

    @Autowired
    public UserResource(UserController userController){
        this.userController = userController;
    }

    @GetMapping
    public List<User> user(){
        return userController.getAllUser();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable("id") int id){
      return userController.findUserById(id);
    }

    @GetMapping("{id}/email")
    public Map<String,String> findByIdAndShowEmail(@PathVariable("id") int id){
        return Collections.singletonMap("email",userController.findUserById(id).getEmail());
    }

    @PostMapping
    public void addUser(@RequestBody UserDto userDto){
        userController.addUser(userDto);
    }

    @DeleteMapping("{id}")
    public void removeUser(@PathVariable("id") int id){
        userController.removeUserById(id);
    }

    @PutMapping("{id}")
    public void updateByIdUser(@RequestBody UserDto userDto, @PathVariable int id){
        userController.updateUser(userDto, id);
    }

    @PatchMapping(path = "{id}")
    public void updatePartialUser(@PathVariable int id, @RequestBody JsonPatch patch) {
        try {
            userController.applyPatchToUser(patch, id);
        } catch (JsonPatchException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}