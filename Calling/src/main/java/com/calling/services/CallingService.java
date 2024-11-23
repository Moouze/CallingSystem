package com.calling.services;

import com.calling.entities.Calling;
import com.calling.entities.Users;
import com.calling.repository.CallingRepository;
import com.calling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallingService {

    @Autowired
    CallingRepository callingRepository;

    @Autowired
    UserRepository userRepository;

    public Calling createCalling(Long userId, String subject, String description) {
        Users user = userRepository.getById(userId);

        Calling calling = new Calling();
        calling.setSubject(subject);
        calling.setDescription(description);

        return callingRepository.save(calling);
    }
}
