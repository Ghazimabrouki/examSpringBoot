package com.arctic.ghazi_mabrouki_4_ArcTic3.services;

import com.arctic.ghazi_mabrouki_4_ArcTic3.entities.*;
import com.arctic.ghazi_mabrouki_4_ArcTic3.repositories.ClientRepository;
import com.arctic.ghazi_mabrouki_4_ArcTic3.repositories.ColisRepository;
import com.arctic.ghazi_mabrouki_4_ArcTic3.repositories.LivreurRepository;
import com.arctic.ghazi_mabrouki_4_ArcTic3.repositories.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements IDeliveryService {

    private final ClientRepository clientRepository;
    private final ColisRepository colisRepository;
    private final LivreurRepository livreurRepository;
    private final ProduitRepository produitRepository;

    @Override
    public Client ajouterClient(Client cl) {
        return clientRepository.save(cl);
    }

    @Override
    public Colis ajouterColisEtAffecterAClient(Colis c, Long idClient) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Client mch mawjoud"));

        c.setClient(client);
        return colisRepository.save(c);
    }

    @Override
    @Transactional
    public void ajouterLivreurEtAffecterColisALivreur(Livreur l, List<String> referenceColis) {
        Livreur savedLivreur = livreurRepository.save(l);

        List<Colis> colisList = colisRepository.findByReferenceColisIn(referenceColis);

        for (Colis colis : colisList) {
            colis.setLivreur(savedLivreur);
        }

        colisRepository.saveAll(colisList);
    }

    @Override
    @Scheduled(fixedRate = 20000)
    public void mettreAJourEtAfficherColis() {
        List<Colis> pendingColis = colisRepository.findByEtatColis(Etat.EN_ATTENTE);

        if (pendingColis.isEmpty()) {
            return;
        }

        for (Colis colis : pendingColis) {
            colis.setEtatColis(Etat.EN_COURS);
        }

        colisRepository.saveAll(pendingColis);

        System.out.println("Updated colis  EN_ATTENTE to EN_COURS");

        for (Colis colis : pendingColis) {
            System.out.println("Colis: " + colis.getReferenceColis());
        }
    }

    @Override
    public List<Client> afficherClients(Categorie categorieProd, LocalDate dateLivraison) {
        return clientRepository.findClientsByProductCategoryAndDeliveryDateAfter(categorieProd, dateLivraison);
    }

    @Override
    public float montantAPayerParClient(String referenceColis) {
        Colis colis = colisRepository.findByReferenceColis(referenceColis)
                .orElseThrow(() -> new RuntimeException("Colis mch mawjoud"));

        float prixCommande = colis.getPrixCommande();

        if (prixCommande >= 100) {
            return prixCommande;
        } else {
            return prixCommande + 8;
        }
    }

    @Override
    public Client clientFidele() {
        List<Client> loyalClients = clientRepository.findMostLoyalClient(Etat.ANNULE);

        if (loyalClients.isEmpty()) {
            return null;
        }

        return loyalClients.get(0);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Colis> getAllColis() {
        return colisRepository.findAll();
    }

    @Override
    public List<Livreur> getAllLivreurs() {
        return livreurRepository.findAll();
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit ajouterProduit(Produit produit) {
        return produitRepository.save(produit);
    }
}
