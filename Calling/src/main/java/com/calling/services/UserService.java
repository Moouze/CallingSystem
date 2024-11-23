package com.calling.services;

import com.calling.dto.UserDTO;
import com.calling.entities.Users;
import com.calling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        Users user = new Users();
        user.setName(userDTO.getName());

        user = userRepository.save(user);

        return new UserDTO(user.getId(), user.getName());
    }

    public UserDTO findUserById(Long id) {

        Optional<Users> user = userRepository.findById(id);

        if (user.isPresent()) {
            Users users = user.get();
            return new UserDTO(users.getId(), users.getName());
        } else {
            throw new RuntimeException("User not found");
        }
    }


}
