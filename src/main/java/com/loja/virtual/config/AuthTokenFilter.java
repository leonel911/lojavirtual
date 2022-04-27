package com.loja.virtual.config;

import com.loja.virtual.domain.Cliente;
import com.loja.virtual.domain.Usuario;
import com.loja.virtual.repositories.ClienteRepository;
import com.loja.virtual.repositories.UsuarioRepository;
import com.loja.virtual.resources.exceptions.InvalidLoginException;
import com.loja.virtual.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClienteRepository clienteRepository;

    public AuthTokenFilter(TokenService tokenService, ClienteRepository clienteRepository) {
        this.tokenService = tokenService;
        this.clienteRepository = clienteRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = recuperarToken(request);
        boolean valido = tokenService.isValid(token);
        if (valido){
            Integer userId = authCliente(token);
            request.setAttribute("id", userId);
        }
        filterChain.doFilter(request, response);
    }

    private Integer authCliente(String token) {
        Integer userId = tokenService.getUserId(token);
        Optional<Cliente> optionalUsuario = clienteRepository.findById(userId);
        if(!optionalUsuario.isPresent()){
            throw new InvalidLoginException("Usuário não encontrado!");
        }
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(optionalUsuario.get().getCpfOuCnpj(),
                        optionalUsuario.get().getSenha(),
                        Collections.singletonList(new SimpleGrantedAuthority(optionalUsuario.get().getPerfil()
                        .getDescricao()))
                        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userId;
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
