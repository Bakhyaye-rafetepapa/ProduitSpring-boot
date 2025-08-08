package sn.edu.isep.produitManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isep.produitManager.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByUsername(String username);
}
