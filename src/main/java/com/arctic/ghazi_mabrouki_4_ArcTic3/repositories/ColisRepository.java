package com.arctic.ghazi_mabrouki_4_ArcTic3.repositories;

import com.arctic.ghazi_mabrouki_4_ArcTic3.entities.Colis;
import com.arctic.ghazi_mabrouki_4_ArcTic3.entities.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {
    
    List<Colis> findByEtatColis(Etat etat);
    
    Optional<Colis> findByReferenceColis(String reference);
    
    List<Colis> findByReferenceColisIn(List<String> references);


}
