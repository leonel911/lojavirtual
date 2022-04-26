package com.loja.virtual.services;

import com.loja.virtual.domain.AutenticacaoDto;
import com.loja.virtual.domain.Cliente;
import com.loja.virtual.domain.DadosLogin;
import com.loja.virtual.domain.Usuario;
import com.loja.virtual.repositories.ClienteRepository;
import com.loja.virtual.repositories.UsuarioRepository;
import com.loja.virtual.resources.exceptions.InvalidLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ImplUserDetailService implUserDetailService;

    public AutenticacaoDto doLogin(DadosLogin dadosLogin){
   /*     UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(dadosLogin.getCpfOuCnpj(), dadosLogin.getSenha());
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch (Exception exception){
            throw new InvalidLoginException("Usuário e/ou senha inválido");
        } */
        Optional<Cliente> cliente = clienteRepository.findByCpfOuCnpjIgnoreCase(dadosLogin.getCpfOuCnpj());
        UserDetails userDetails = implUserDetailService.loadUserByUsername(dadosLogin.getCpfOuCnpj());
        String token = tokenService.generateToken(userDetails, cliente.get());
        return new AutenticacaoDto(token, "Bearer");
    }
}
