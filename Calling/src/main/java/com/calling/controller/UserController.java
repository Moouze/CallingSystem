package com.calling.controller;

import com.calling.entities.Users;
import com.calling.repository.CompanyRepository;
import com.calling.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    //metodo para adicionar um novo usuário
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    //metodo para buscar todos os usuários
    @GetMapping
    public ResponseEntity<List<Users>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    //metodo para buscar usuários pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return userRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //metodo para atualizar usuários
    @PutMapping
    public ResponseEntity<Users> putUser(@Valid @RequestBody Users user) {
        return userRepository.findById(user.getId()).map(x -> ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //metodo para deletar usuários
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        Optional<Users> user = userRepository.findById(id);

        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        userRepository.deleteById(id);
    }


}
