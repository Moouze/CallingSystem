package com.calling.repository;

import com.calling.entities.Calling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallingRepository extends JpaRepository<Calling, Long> {
}
