package com.loja.virtual.services;

import com.loja.virtual.domain.Cliente;
import com.loja.virtual.domain.Usuario;
import com.loja.virtual.repositories.ClienteRepository;
import com.loja.virtual.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplUserDetailService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findByCpfOuCnpjIgnoreCase(username);
        if (!cliente.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(cliente.get().getPerfil().getDescricao()));

        return new User(
                cliente.get().getCpfOuCnpj(),
                cliente.get().getSenha(),
                true,
                true,
                true,
                true,
                authorities);

    }

}
