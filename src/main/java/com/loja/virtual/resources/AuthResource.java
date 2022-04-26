package com.loja.virtual.resources;

import com.loja.virtual.domain.AutenticacaoDto;
import com.loja.virtual.domain.Cliente;
import com.loja.virtual.domain.ConfirmationToken;
import com.loja.virtual.domain.DadosLogin;
import com.loja.virtual.repositories.ClienteRepository;
import com.loja.virtual.repositories.ConfirmationTokenRepository;
import com.loja.virtual.services.AutenticacaoService;
import com.loja.virtual.services.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AutenticacaoDto> autenticar(@RequestBody DadosLogin dadosLogin) {
            return ResponseEntity.accepted().body(autenticacaoService.doLogin(dadosLogin));

    }

    @RequestMapping(value = "/{confirmationToken}", method=RequestMethod.GET)
    public ResponseEntity<Void> confirmToken(@PathVariable String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null){
            if (token.getExpiresAt() == null){
                token.setExpiresAt(LocalDateTime.now().plusSeconds(20));
            }
            LocalDateTime expiredAt = token.getExpiresAt();
            if (expiredAt.isBefore(LocalDateTime.now())) {
                throw new AuthorizationException("Token expirado! Solicite um novo token para confirmação de conta.");
            }
            clienteRepository.save(token.getCliente());

        } else {
            throw new IllegalStateException("Link inválido! Solicite um novo link pelo app");
        }
        return ResponseEntity.noContent().build();
    }
}

