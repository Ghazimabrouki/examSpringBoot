package com.arctic.ghazi_mabrouki_4_ArcTic3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Colis implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idColis;
    
    private String referenceColis;
    
    private Float prixCommande;
    
    private LocalDate dateLivraison;
    
    @Enumerated(EnumType.STRING)
    private Etat etatColis;
    
    @ManyToOne
    private Client client;
    
    @ManyToOne
    private Produit produit;
    
    @ManyToOne
    private Livreur livreur;
}
