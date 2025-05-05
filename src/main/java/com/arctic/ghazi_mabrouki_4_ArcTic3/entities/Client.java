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
public class Client implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    
    private String nomClient;
    
    private String numTel;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Colis> colis = new ArrayList<>();
}
