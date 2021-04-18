package com.active.store.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@Component
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private Long id;
    @Column(name = "ACTIVE_POINTS")
    private Integer activePoints;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID",
            referencedColumnName = "CUSTOMER_ID",
            foreignKey = @javax.persistence
                    .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @JsonBackReference
    @ToString.Exclude
    private Customer customer;


    public Product(Long id, Integer activePoints) {
        this.id = id;
        this.activePoints = activePoints;
    }
}
