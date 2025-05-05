package com.arctic.ghazi_mabrouki_4_ArcTic3.repositories;

import com.arctic.ghazi_mabrouki_4_ArcTic3.entities.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, Long> {
}
