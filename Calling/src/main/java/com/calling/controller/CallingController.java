package com.calling.controller;

import com.calling.entities.Calling;
import com.calling.repository.CallingRepository;
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
@RequestMapping("/calls")
public class CallingController {

    @Autowired
    private CallingRepository callingRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Calling> createCall(@Valid @RequestBody Calling calling) {
        if (userRepository.existsById(calling.getUsers().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(callingRepository.save(calling));

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found", null);
    }

    @GetMapping
    public ResponseEntity<List<Calling>> getAllCallings() {
        return ResponseEntity.ok(callingRepository.findAll());
    }

    @PutMapping
    public ResponseEntity<Calling> updateCalling(@Valid @RequestBody Calling calling) {
        if (callingRepository.existsById(calling.getId())) {

            if (userRepository.existsById(calling.getUsers().getId())) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(callingRepository.save(calling));
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCalling(@PathVariable Long id) {
        Optional<Calling> calling = callingRepository.findById(id);

        if (calling.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        callingRepository.deleteById(id);
    }

}
