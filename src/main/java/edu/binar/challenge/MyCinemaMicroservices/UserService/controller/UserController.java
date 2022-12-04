package edu.binar.challenge.MyCinemaMicroservices.UserService.controller;

import edu.binar.challenge.MyCinemaMicroservices.UserService.converter.UserConverter;
import edu.binar.challenge.MyCinemaMicroservices.UserService.entity.User;
import edu.binar.challenge.MyCinemaMicroservices.UserService.entity.dto.UserDto;
import edu.binar.challenge.MyCinemaMicroservices.UserService.exception.ResourceNotFoundException;
import edu.binar.challenge.MyCinemaMicroservices.UserService.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/mycinema-v1")
public class UserController {

    @Autowired
    private UserService userService;

    private ModelMapper modelMapper;

    @GetMapping("/users/")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @GetMapping("/users/{userId}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        UserDto userResponse = UserConverter.convertEntityToDto(user);

        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("/users")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User userRequest = UserConverter.convertDtoToEntity(userDto);
        User user = userService.createUser(userRequest);

        UserDto userResponse = UserConverter.convertEntityToDto(user);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "userId") Long userId, @Valid @RequestBody UserDto userDto) throws ResourceNotFoundException {
        User userRequest = UserConverter.convertDtoToEntity(userDto);
        User user = userService.updateUser(userId, userRequest);

        UserDto userResponse = UserConverter.convertEntityToDto(user);

        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping("/users/{userId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        userService.deleteUser(userId);

        return ResponseEntity.ok("User with ID(" + userId + ") deleted successfully");
    }

    @GetMapping(value = "/secure")
    public String getSecure() {
        return "Secure endpoint available";
    }
}
