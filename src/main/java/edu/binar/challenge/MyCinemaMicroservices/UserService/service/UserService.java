package edu.binar.challenge.MyCinemaMicroservices.UserService.service;

import edu.binar.challenge.MyCinemaMicroservices.UserService.entity.User;
import edu.binar.challenge.MyCinemaMicroservices.UserService.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long userId) throws ResourceNotFoundException;
    User createUser(User user);
    User updateUser(Long userId, User userDetails) throws ResourceNotFoundException;
    void deleteUser(Long userId) throws ResourceNotFoundException;
}
