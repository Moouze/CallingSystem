package com.calling.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_response_calls")
public class ResponseCalls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String response;

    @UpdateTimestamp
    private LocalDateTime responseTime;

    @ManyToOne
    @JoinColumn(name = "calls_id")
    private Calling calling;

    @ManyToOne
    @JoinColumn(name = "technical_id")
    private Users technical;


}
