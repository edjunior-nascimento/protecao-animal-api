package com.api.protecaoanimal.configuration.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.models.UsuariosModel;
import com.api.protecaoanimal.repositories.UsuariosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    final UsuariosRepository usuariosRepository;

    public UserDetailsServiceImpl(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuariosModel usuariosModel = usuariosRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
            return new User(usuariosModel.getUsername(), usuariosModel.getPassword(), true, true, true,true, usuariosModel.getAuthorities());
    }
    
}
