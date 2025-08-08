package sn.edu.isep.produitManager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isep.produitManager.entities.Produit;
import sn.edu.isep.produitManager.services.ProduitService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/produits")
@Tag(name = "Gestion des produits", description = "Opérations CRUD sur les produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    // Lire tous les produits
    @GetMapping
    @Operation(summary = "Lister tous les produits")
    public ResponseEntity<List<Produit>> getAllProduits() {
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    // Lire un produit par ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un produit par son ID")
    public ResponseEntity<?> getProduitById(@PathVariable Long id) {
        Optional<Produit> produit=produitService.getProduitById(id);
        if (produit.isPresent()){
            return ResponseEntity.ok(produit.get());
        }else {
            return ResponseEntity.status(404).body("Produit non trouvé");
        }

    }

    // Créer un nouveau produit
    @PostMapping
    @Operation(summary = "Créer un nouveau produit")
    public ResponseEntity<?> createProduit(@RequestBody Produit produit) {
        if (produit.getNom() == null || produit.getNom().isBlank()) {
            return ResponseEntity.badRequest().body("Le nom du produit est obligatoire.");
        }

        Optional<Produit> existant = produitService.produitParNom(produit.getNom());
        if (existant.isPresent()) {
            return ResponseEntity.status(409)
                    .body("Un produit nommé '" + produit.getNom() + "' existe déjà.");
        }

        Produit nouveau = produitService.createProduit(produit);
        return ResponseEntity.status(201).body(nouveau);
    }

    //Modifier un produit existant
    @PutMapping("/{id}")
    @Operation(summary = "Modifier un produit existant")
    public ResponseEntity<?> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        if (produit.getNom() == null || produit.getNom().isBlank()) {
            return ResponseEntity.badRequest().body("Le nom est obligatoire.");
        }
        Optional<Produit> existant = produitService.produitParNom(produit.getNom());
        if (existant.isPresent()) {
            return ResponseEntity.status(409)
                    .body("Un produit nommé '" + produit.getNom() + "' existe déjà.");
        }

        Optional<Produit> res=produitService.updateProduit(id, produit);
        return ResponseEntity.ok(res.get());
    }

    // Supprimer un produit
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un produit")
    public ResponseEntity<?> deleteProduit(@PathVariable Long id) {
        boolean deleted = produitService.deleteProduit(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(404).body("Produit non trouvé");
    }
}