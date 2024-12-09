package com.calling.service;

import com.calling.entities.UserLogin;
import com.calling.entities.Users;
import com.calling.repository.UserRepository;
import com.calling.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Optional<Users> RegisterUser(Users user) {
        if (userRepository.findByUser(user.getEmail()).isPresent())
            return Optional.empty();

        user.setPassword(criptografarSenha(user.getPassword()));
        return Optional.of(userRepository.save(user));
    }

    public Optional<Users> updateUser(Users user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            Optional<Users> searchUser = userRepository.findByUser(user.getEmail());

            if ((searchUser.isPresent()) && (searchUser.get().getId() != user.getId()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use", null);

            user.setPassword(criptografarSenha(user.getPassword()));

            return Optional.ofNullable(userRepository.save(user));
        }
            return Optional.empty();
    }

    public Optional<UserLogin> authenticateUser (Optional<UserLogin> userLogin) {
        var credentials = new UsernamePasswordAuthenticationToken(userLogin.get().getEmail(), userLogin.get().getPassword());

        Authentication authentication = authenticationManager.authenticate(credentials);

        if (authentication.isAuthenticated()) {
            Optional<Users> user = userRepository.findByUser(userLogin.get().getEmail());

            if (user.isPresent()) {

                userLogin.get().setId(user.get().getId());
                userLogin.get().setName(user.get().getName());
                userLogin.get().setRole(user.get().getRole());
                userLogin.get().setToken(gerarToken(userLogin.get().getEmail()));
                userLogin.get().setPassword("");

                return userLogin;
            }
        }
                return Optional.empty();
    }

    private String criptografarSenha(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private String gerarToken(String email) {
        return "Bearer " + jwtService.generateToken(email);
    }
}
