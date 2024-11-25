package com.calling.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
