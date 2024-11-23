package com.calling.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table (name = "tb_calling")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String subject;
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

}
