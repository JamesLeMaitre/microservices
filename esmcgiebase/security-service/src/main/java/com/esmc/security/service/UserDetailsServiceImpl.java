package com.esmc.security.service;

import com.esmc.security.entity.AppUser;
import com.esmc.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AppUser utilisateur = utilisateurRepository.findByUsername(login);
        if (utilisateur != null) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            if (utilisateur.getRoles() != null) {
                utilisateur.getRoles().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRolename()));
                });
            }
            return new User(utilisateur.getUsername(), utilisateur.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("Utilisateur non trouvé!");
    }
}
