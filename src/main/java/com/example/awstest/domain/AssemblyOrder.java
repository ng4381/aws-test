package com.example.awstest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class AssemblyOrder {
    @Id
    @GeneratedValue
    private Long id;
    private Long extOrderId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assemblyOrder")
    private List<AssemblyOrderDetail> assemblyOrderDetails = new ArrayList<>();

}
