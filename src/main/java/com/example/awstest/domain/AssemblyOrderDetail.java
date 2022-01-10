package com.example.awstest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class AssemblyOrderDetail {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private int qty;

    @ManyToOne
    private AssemblyOrder assemblyOrder;

    @OneToMany(mappedBy = "assemblyOrderDetail")
    private List<AssemblyOrderDetailStage> assemblyOrderDetailStages = new ArrayList<>();
}
