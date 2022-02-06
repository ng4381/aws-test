package com.example.awstest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AssemblyOrderDetail {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Product product;
    private int qty;

    @ManyToOne
    @JoinColumn(name = "assembly_order_id")
    private AssemblyOrder assemblyOrder;

    @Override
    public String toString() {
        return "AssemblyOrderDetail{" +
                "id=" + id +
                ", productId =" + product.getId() +
                ", qty=" + qty +
                ", assemblyOrder=" + assemblyOrder +
                '}';
    }
}
