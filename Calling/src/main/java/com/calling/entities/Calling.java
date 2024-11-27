package com.calling.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table (name = "tb_calling")
@Data
public class Calling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String subject;

    @NotNull
    private String description;

    @UpdateTimestamp
    private LocalDateTime data;

    @JoinColumn(name = "users_id")
    @ManyToOne
    private Users users;

}
