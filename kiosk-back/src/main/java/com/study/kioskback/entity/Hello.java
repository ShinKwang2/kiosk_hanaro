package com.study.kioskback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Hello {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
}
