package com.api.protecaoanimal.configuration.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public UsuariosModel authenticate(String username, String password) {
        UsuariosModel usuariosModel = usuariosRepository.findByLogin(username).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        String hashedPassword = hashPassword(password, usuariosModel.getSalt());
        if(hashedPassword.equals(usuariosModel.getPassword())){
            return usuariosModel;
        }else{
            return null;
        }
    }

    private String hashPassword(String password, String salt) {
        String hashedPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((password + salt).getBytes(StandardCharsets.UTF_8));
            hashedPassword = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }
}
