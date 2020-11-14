package com.kemisshop.catalogservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = -1665555919153901140L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @Enumerated(EnumType.STRING)   // Please always remember this
    private Category category;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public ProductCategory(Category category) {
        this.category = category;
    }

    public Product addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
        return product;
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
