package com.arctic.ghazi_mabrouki_4_ArcTic3.repositories;

import com.arctic.ghazi_mabrouki_4_ArcTic3.entities.Categorie;
import com.arctic.ghazi_mabrouki_4_ArcTic3.entities.Client;
import com.arctic.ghazi_mabrouki_4_ArcTic3.entities.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    @Query("SELECT DISTINCT c.client FROM Colis c WHERE c.produit.categorieProd = :categorie AND c.dateLivraison > :date")
    List<Client> findClientsByProductCategoryAndDeliveryDateAfter(
            @Param("categorie") Categorie categorie, 
            @Param("date") LocalDate date);
    
    @Query("SELECT c.client FROM Colis c WHERE c.etatColis <> :etat GROUP BY c.client ORDER BY COUNT(c) DESC")
    List<Client> findMostLoyalClient(@Param("etat") Etat etat);


}
