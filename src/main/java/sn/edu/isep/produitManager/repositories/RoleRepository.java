package sn.edu.isep.produitManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isep.produitManager.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNom(String nom);
}
