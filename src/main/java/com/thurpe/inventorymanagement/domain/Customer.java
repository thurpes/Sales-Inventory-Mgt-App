package com.thurpe.inventorymanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 20)
    private String firstname;

    @Column(nullable = false)
    @Size(min = 3, max = 20)
    private String lastname;

    @Column(nullable = false)
    private Long phone;

    @CreationTimestamp
    private Instant createdDate;

    @UpdateTimestamp
    private Instant lastModifiedDate;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY)
    private List<Order> orders;
}
