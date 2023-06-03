package com.klass.lab01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Exception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String transactionId;
    private Date date;
    private String time;
    private String principle;
    private String operation;
    private String exceptionType;
}
