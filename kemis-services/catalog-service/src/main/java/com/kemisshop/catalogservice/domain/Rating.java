package com.kemisshop.catalogservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Rating implements Serializable {
    private static final long serialVersionUID = 2140740736594930748L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private UUID ratingPublicId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @NotNull
    @Column(unique = true)
    private UUID buyerPublicId;

    @NotNull
    private Integer rating;

    @NotBlank
    @Column(length = 2000)
    private String review;

    @JsonIgnore
    private Boolean approved = false;

    public void approve() {
        this.approved = true;
    }


}
