package com.kemisshop.catalogservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Product_Catalogue")
public class Product implements Serializable {

    private static final long serialVersionUID = -1214449184077968614L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Owner_Id")
    @NotNull
    private UUID publicSellerId;

    @Column(name = "Public_Id")
    @NotNull
    private UUID publicProductId;

    @Column(name = "Product_Name", unique = true)
    @Size(min = 4, max = 100)
    private String name;

    @Digits(integer= 5, fraction = 2)
    @Column(name = "Product_Price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_Id", nullable = false)
    private ProductCategory category;

    @NotNull
    @Column(name="units")
    private Integer unitsInStock;

    @NotBlank
    @Column(name = "Product_Description")
    private String description;

    @Column(name = "Image_name", unique = true)
    private String imageName;

    @JsonIgnore
    private Double averageRating;

    @JsonIgnore
    private boolean approved;

    public void approve() {
        this.approved = true;
    }

}