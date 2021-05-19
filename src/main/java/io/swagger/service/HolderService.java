package io.swagger.service;

import io.swagger.model.Holder;
import io.swagger.repository.HolderRepository;
import io.swagger.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HolderService {

    @Autowired
    private HolderRepository holderRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String password) {

        try {
            Holder holder = holderRepository.findByEmail(email);
            String salt = holder.getSalt();
            password = password + salt;

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            return jwtTokenProvider.createToken(email, holder.getRole());
        } catch (AuthenticationException ex) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email and/or password incorrect");
        }
    }

    public Holder add(Holder holder) {
        // TO DO VOOR RENE: CREEER FUNCTIE DIE SALT MAAKT EN TOEVOEGT
        // DUS holder.setPassword(passwordEncoder.encode(holder.getPassword() + holder.getSalt()))
        if(holderRepository.findByEmail(holder.getEmail()) == null) {
            holder.setPassword(passwordEncoder.encode(holder.getPassword()));
            holderRepository.save(holder);
            return holder;
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already in use");
        }
    }
}
