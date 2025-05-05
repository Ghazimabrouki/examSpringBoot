package com.arctic.ghazi_mabrouki_4_ArcTic3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produit implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    
    private String identifiant;
    
    @Enumerated(EnumType.STRING)
    private Categorie categorieProd;
}
