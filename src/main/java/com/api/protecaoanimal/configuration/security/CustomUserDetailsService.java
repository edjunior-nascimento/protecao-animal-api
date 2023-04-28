package com.api.protecaoanimal.configuration.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;
import org.webjars.NotFoundException;

import com.api.protecaoanimal.models.RegrasModel;
import com.api.protecaoanimal.models.UsuariosModel;
import com.api.protecaoanimal.repositories.UsuariosRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    final UsuariosRepository usuariosRepository;

    public CustomUserDetailsService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UsuariosModel usuariosModel = usuariosRepository.findByLogin(login).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        return new User(usuariosModel.getUsername(), usuariosModel.getPassword(), getAuthorities(usuariosModel));
    }

    private List<GrantedAuthority> getAuthorities(UsuariosModel usuariosModel) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RegrasModel regrasModel : usuariosModel.getRegras()) {
            authorities.add(new SimpleGrantedAuthority(regrasModel.getNome()));
        }
        return authorities;
    }

}
