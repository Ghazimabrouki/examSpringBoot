package com.arctic.ghazi_mabrouki_4_ArcTic3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livreur implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivreur;
    
    private String nomLivreur;
    
    private String prenomLivreur;
    
    @OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Colis> colis = new ArrayList<>();
}
