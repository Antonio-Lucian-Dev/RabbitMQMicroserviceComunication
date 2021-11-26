package com.asusoftware.userapi.service;

import com.asusoftware.userapi.model.User;
import com.asusoftware.userapi.model.dto.NotificationDto;
import com.asusoftware.userapi.model.dto.UserDto;
import com.asusoftware.userapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void create(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        userRepository.save(user);
    }

    public NotificationDto createNotification(UUID id, NotificationDto notificationDto) {
        User user = findUserById(id);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        notificationDto.setUser(userDto);
        return notificationDto;
    }

    private User findUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            return userDto;
        }).collect(Collectors.toList());
    }
}
