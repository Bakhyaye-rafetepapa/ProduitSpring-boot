package sn.edu.isep.produitManager.services;


import org.springframework.stereotype.Service;
import sn.edu.isep.produitManager.entities.Produit;
import sn.edu.isep.produitManager.repositories.ProduitRepository;

import java.util.List;
import java.util.Optional;



@Service
    public class ProduitService {

        private final ProduitRepository produitRepository;

        public ProduitService(ProduitRepository produitRepository) {
            this.produitRepository = produitRepository;
        }

        public List<Produit> getAllProduits() {
            return produitRepository.findAll();
        }

        public Optional<Produit> getProduitById(Long id) {
            return produitRepository.findById(id);
        }

        public Produit createProduit(Produit produit) {
            return produitRepository.save(produit);
        }

        public Optional<Produit> updateProduit(Long id, Produit updatedProduit) {
            return produitRepository.findById(id).map(produit -> {
                produit.setNom(updatedProduit.getNom());
                produit.setDescription(updatedProduit.getDescription());
                produit.setPrixUnitaire(updatedProduit.getPrixUnitaire());
                return produitRepository.save(produit);
            });
        }

        public boolean deleteProduit(Long id) {
            if (!produitRepository.existsById(id)) {
                return false;
            }
            produitRepository.deleteById(id);
            return true;
        }
    public Optional<Produit> produitParNom(String nom){
        return produitRepository.findByNom(nom);
    }

    }



