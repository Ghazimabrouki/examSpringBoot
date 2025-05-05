package com.arctic.ghazi_mabrouki_4_ArcTic3.services;

import com.arctic.ghazi_mabrouki_4_ArcTic3.entities.*;

import java.time.LocalDate;
import java.util.List;

public interface IDeliveryService {
    
    Client ajouterClient(Client cl);
    
    Colis ajouterColisEtAffecterAClient(Colis c, Long idClient);
    
    void ajouterLivreurEtAffecterColisALivreur(Livreur l, List<String> referenceColis);
    
    void mettreAJourEtAfficherColis();
    
    List<Client> afficherClients(Categorie categorieProd, LocalDate dateLivraison);
    
    float montantAPayerParClient(String referenceColis);
    
    Client clientFidele();
    
    List<Client> getAllClients();
    
    List<Colis> getAllColis();
    
    List<Livreur> getAllLivreurs();
    
    List<Produit> getAllProduits();
    
    Produit ajouterProduit(Produit produit);
}
