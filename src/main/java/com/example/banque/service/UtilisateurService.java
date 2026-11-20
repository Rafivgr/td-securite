package com.example.banque.service;

import java.util.Optional;
import com.example.banque.utilisateur.Utilisateur;
import com.example.banque.utilisateur.UtilisateurRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UtilisateurService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Utilisateur> res = utilisateurRepository.findByLogin(login);
        if (res.isPresent()) {
            Utilisateur u = res.get();
            return User.withUsername(u.getLogin())
                    .password(u.getMotDePasse())
                    .roles(u.getRole().name())
                    .build();
        } else {
            throw new UsernameNotFoundException(login);
        }
}
}