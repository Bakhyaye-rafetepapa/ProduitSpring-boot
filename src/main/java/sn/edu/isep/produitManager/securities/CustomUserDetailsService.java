package sn.edu.isep.produitManager.securities;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sn.edu.isep.produitManager.entities.Utilisateur;
import sn.edu.isep.produitManager.repositories.UtilisateurRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
    public class CustomUserDetailsService  implements UserDetailsService {
        @Autowired
        private UtilisateurRepository utilisateurRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Utilisateur user = utilisateurRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("Utilisateur non trouvé");
            }
            Set<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNom()))
                    .collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

        }

    }

