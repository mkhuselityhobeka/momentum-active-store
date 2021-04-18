package com.active.store.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "CUSTOMER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Customer {

    @Id
    @Column(name = "CUSTOMER_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name="ACTIVE_POINTS")
    private Integer activePoints;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "customer", fetch = FetchType.EAGER,orphanRemoval = true)
    @Column(name="PRODUCT_ID")
    @JsonManagedReference
    @ToString.Exclude
    private List<Product> products = new ArrayList<> ();
}
