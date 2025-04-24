package com.kickproject.user.services;

import com.kickproject.user.dto.UserDto;
import com.kickproject.user.entities.User;
import com.kickproject.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getUser(String userPhone, Long userId) {
        Optional<User> userOptional = userRepository.findByIdAndPhone(userId, userPhone);
        User user = userOptional.orElseThrow(() -> new RuntimeException("Отсутствует пользователь с id " + userId + " либо с телефоном" + userPhone));

        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getPhone()
        );
    }

    @Override
    public UserDto saveUser(UserDto request) {
        Optional<User> optionalUser = userRepository.findByPhone(request.getPhone());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDto(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPatronymic(),
                    user.getPhone()
            );
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPatronymic(request.getPatronymic());
        user.setPhone(request.getPhone());
        userRepository.save(user);
        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getPhone()
        );
    }

    @Override
    public void deleteUser(String userPhone, Long userId) {
        Optional<User> userOptional = userRepository.findByIdAndPhone(userId, userPhone);
        User user = userOptional.orElseThrow(() -> new RuntimeException("Отсутствует пользователь с id " + userId + " либо с телефоном" + userPhone));
        userRepository.delete(user);
    }

    @Override
    public UserDto editUser(String userPhone, Long userId, UserDto request) {
        Optional<User> userOptional = userRepository.findByIdAndPhone(userId, userPhone);
        User user = userOptional.orElseThrow(() -> new RuntimeException("Отсутствует пользователь с id " + userId + " либо с телефоном" + userPhone));
        if (request.getFirstName() != null && !request.getFirstName().equals("")) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null && !request.getLastName().equals("")) {
            user.setLastName(request.getLastName());
        }
        if (request.getPatronymic() != null && !request.getPatronymic().equals("")) {
            user.setPatronymic(request.getPatronymic());
        }
        userRepository.save(user);
        return new UserDto(
                user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getPhone()
        );
    }
}
