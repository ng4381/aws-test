package com.example.awstest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class AssemblyOrder {
    @Id
    @GeneratedValue
    private Long id;
    private Long extOrderId;
}
