package com.calling.repository;

import com.calling.entities.ResponseCalls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<ResponseCalls, Long> {

}
