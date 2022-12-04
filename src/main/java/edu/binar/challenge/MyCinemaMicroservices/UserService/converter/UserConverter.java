package edu.binar.challenge.MyCinemaMicroservices.UserService.converter;

import edu.binar.challenge.MyCinemaMicroservices.UserService.entity.User;
import edu.binar.challenge.MyCinemaMicroservices.UserService.entity.dto.UserDto;
import org.modelmapper.ModelMapper;

public class UserConverter {

    private UserConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static User convertDtoToEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    public static UserDto convertEntityToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
