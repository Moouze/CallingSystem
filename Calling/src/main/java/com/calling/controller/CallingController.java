package com.calling.controller;
import com.calling.entities.Calling;
import com.calling.repository.CallingRepository;
import com.calling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calls")
public class CallingController {

    @Autowired
    private CallingRepository callingRepository;

    @Autowired
    private UserRepository userRepository;

   @PostMapping
    public ResponseEntity<Calling> createCall(@RequestBody Calling calling) {
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(callingRepository.save(calling));
   }

   @GetMapping
    public ResponseEntity<List<Calling>> getAllCallings() {
       return ResponseEntity.ok(callingRepository.findAll());
   }


}
