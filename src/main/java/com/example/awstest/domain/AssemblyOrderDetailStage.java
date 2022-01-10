package com.example.awstest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class AssemblyOrderDetailStage {
    @Id
    @GeneratedValue
    private Long id;
    private int qty;

    private LocalDateTime dateTime;

    private String pf;

    @ManyToOne
    private AssemblyOrderDetail assemblyOrderDetail;

    @OneToOne
    private Stage stage;
}
