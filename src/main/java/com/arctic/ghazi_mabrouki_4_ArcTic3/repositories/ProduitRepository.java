package com.arctic.ghazi_mabrouki_4_ArcTic3.repositories;

import com.arctic.ghazi_mabrouki_4_ArcTic3.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
