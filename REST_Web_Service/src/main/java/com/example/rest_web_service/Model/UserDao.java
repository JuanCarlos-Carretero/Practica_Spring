package com.example.rest_web_service.Model;

import com.example.rest_web_service.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findAll();

    Optional<User> findById(Integer integer);

    void delete(User entity);

    <S extends User> S save(S entity);
}
