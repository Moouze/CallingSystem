package com.calling.service;

import com.calling.entities.Role;
import com.calling.entities.Users;
import org.springframework.stereotype.Service;

@Service
public class CallingService {

    public void responseCalls (Long callsId, Users users, String response) {

        if (users.getRole() != Role.Technical){
            throw new IllegalStateException("User is not a technical user");
        }

    }
}
