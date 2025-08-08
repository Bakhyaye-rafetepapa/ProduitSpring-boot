package sn.edu.isep.produitManager.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.edu.isep.produitManager.entities.Produit;

import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{
    Optional<Produit> findByNom(String nom);
    // Tu peux ajouter des méthodes personnalisées ici si besoin
    }

