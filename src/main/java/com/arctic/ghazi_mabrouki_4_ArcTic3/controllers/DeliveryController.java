package com.arctic.ghazi_mabrouki_4_ArcTic3.controllers;

import com.arctic.ghazi_mabrouki_4_ArcTic3.entities.*;
import com.arctic.ghazi_mabrouki_4_ArcTic3.services.IDeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
@Tag(name = "ghazimabrouki", description = "testghazimabrouki")
public class DeliveryController {
    
    private final IDeliveryService deliveryService;
    
    @PostMapping("/clients")
    @Operation(summary = "Add a new client")
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client) {
        Client savedClient = deliveryService.ajouterClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }
    
    @GetMapping("/clients")
    @Operation(summary = "Get all clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = deliveryService.getAllClients();
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/clients/category-date")
    @Operation(summary = "tgeti les clients eli ordrou prod whith specific categ after given date"    )
    public ResponseEntity<List<Client>> getClientsByCategoryAndDate(
            @RequestParam Categorie categorie,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Client> clients = deliveryService.afficherClients(categorie, date);
        return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/clients/most-loyal")
    @Operation(summary = "clinet fidele")
    public ResponseEntity<Client> getMostLoyalClient() {
        Client client = deliveryService.clientFidele();
        if (client == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(client);
    }
    
    @PostMapping("/colis/{clientId}")
    @Operation(summary = "Add a new colis and torbtou to a client")
    public ResponseEntity<Colis> ajouterColisEtAffecterAClient(
            @RequestBody Colis colis,
            @PathVariable Long clientId) {
        Colis savedColis = deliveryService.ajouterColisEtAffecterAClient(colis, clientId);
        return new ResponseEntity<>(savedColis, HttpStatus.CREATED);
    }
    
    @GetMapping("/colis")
    @Operation(summary = "Get all colis")
    public ResponseEntity<List<Colis>> getAllColis() {
        List<Colis> colisList = deliveryService.getAllColis();
        return ResponseEntity.ok(colisList);
    }
    
    @GetMapping("/colis/payment/{reference}")
    @Operation(summary = "Calculate payment of colis")
    public ResponseEntity<Float> calculatePaymentAmount(@PathVariable String reference) {
        float amount = deliveryService.montantAPayerParClient(reference);
        return ResponseEntity.ok(amount);
    }
    
    @PostMapping("/livreurs")
    @Operation(summary = "Add new livreur and torbt colis lih")
    public ResponseEntity<Void> ajouterLivreurEtAffecterColis(
            @RequestBody Livreur livreur,
            @RequestParam List<String> referenceColis) {
        deliveryService.ajouterLivreurEtAffecterColisALivreur(livreur, referenceColis);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping("/livreurs")
    @Operation(summary = "Get all livreurs")
    public ResponseEntity<List<Livreur>> getAllLivreurs() {
        List<Livreur> livreurs = deliveryService.getAllLivreurs();
        return ResponseEntity.ok(livreurs);
    }
    
    @PostMapping("/produits")
    @Operation(summary = "Add new produit")
    public ResponseEntity<Produit> ajouterProduit(@RequestBody Produit produit) {
        Produit savedProduit = deliveryService.ajouterProduit(produit);
        return new ResponseEntity<>(savedProduit, HttpStatus.CREATED);
    }
    
    @GetMapping("/produits")
    @Operation(summary = "Get all produits")
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = deliveryService.getAllProduits();
        return ResponseEntity.ok(produits);
    }
    
    @PostMapping("/update-colis-status")
    @Operation(summary = "update all colis status --trigger--")
    public ResponseEntity<Void> updateColisStatus() {
        deliveryService.mettreAJourEtAfficherColis();
        return ResponseEntity.ok().build();
    }
}
