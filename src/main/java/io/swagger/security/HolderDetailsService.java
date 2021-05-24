package io.swagger.security;

import io.swagger.model.Holder;
import io.swagger.repository.HolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HolderDetailsService implements UserDetailsService {

    @Autowired
    private HolderRepository holderRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Holder holder = holderRepository.findByEmail(s);

        if(holder == null)
            throw new UsernameNotFoundException("Holder with email " + s + " not found");

        UserDetails userDetails = User
                .withUsername(s)
                .password(holder.getPassword())
                .authorities(holder.getRole())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
        return userDetails;
    }
}
