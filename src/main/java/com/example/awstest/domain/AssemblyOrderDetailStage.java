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
    @JoinColumn(name = "assembly_order_detail_id")
    private AssemblyOrderDetail assemblyOrderDetail;

    @OneToOne
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    private Stage stage;
}
