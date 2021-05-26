package io.swagger.service.Holders;

import io.swagger.model.Account;
import io.swagger.model.Enums.Role;
import io.swagger.model.Holder;
import io.swagger.repository.HolderRepository;
import io.swagger.security.JwtTokenProvider;
import io.swagger.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;

import static java.util.Collections.emptyList;

@Service
public class HolderService {

    @Autowired
    public HolderRepository holderRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String password) {
        try {
            Holder holder = holderRepository.findByEmail(email);
            if(holder == null) {
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email and/or password incorrect");
            }

            String salt = holder.getSalt();
            password = password + salt;
            
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            return jwtTokenProvider.createToken(email, holder.getRole());

        } catch (AuthenticationException ex) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email and/or password incorrect");
        }
    }

    public void addInitialHolders() { // holders
        Random r = new SecureRandom();
        byte[] salt = new byte[20];
        r.nextBytes(salt);

        Holder holder = new Holder();
        holder.setDailyLimit(new BigDecimal("250"));
        holder.setEmail("bank@inholland.nl");
        holder.setFirstName("Inholland");
        holder.setLastName("Bank");
        holder.setRole(Role.ROLE_EMPLOYEE);
        String password = "p4ssw0rd";
        holder.setSalt(salt.toString());
        holder.setStatus(Holder.StatusEnum.ACTIVE);
        String fullPassword = password + salt;
        holder.setPassword(passwordEncoder.encode(fullPassword));
        List<Account> accounts = emptyList();
        System.out.println(accounts);
        holder.setAccounts(accounts);
        holderRepository.save(holder);

        Holder holder2 = new Holder();
        holder2.setDailyLimit(new BigDecimal("250"));
        holder2.setEmail("peter@appel.com");
        holder2.setFirstName("peter");
        holder2.setLastName("appel");
        holder2.setRole(Role.ROLE_CUSTOMER);
        holder2.setSalt(salt.toString());
        holder2.setStatus(Holder.StatusEnum.ACTIVE);
        holder2.setPassword(passwordEncoder.encode(fullPassword));
        holder2.setAccounts(accounts);
        holderRepository.save(holder2);
    }

    public List<Holder> getAllHolders() {

        List<Holder> holders = holderRepository.findAll();

        return holders;
    }

    public Holder getHolderByEmail(String email) {
        Holder holder = holderRepository.findByEmail(email);

        return holder;
    }

    public Holder getHolderById(int id) {

        Holder holder = holderRepository.findById(id);

        return holder;
    }

    public List<Account> getAccountsByHolderId(int id) {
        List<Account> accounts = accountsService.getAllAccountsByHolderId(id);
        return accounts;
    }

    public Holder add(Holder holder) {

        Random r = new SecureRandom();
        byte[] salt = new byte[20];
        r.nextBytes(salt);

        BigDecimal dailyLimit = holder.getDailyLimit();
        String email = holder.getEmail();
        String firstName = holder.getFirstName();
        String lastName = holder.getLastName();
        Role role = holder.getRole();
        String password = holder.getPassword();
        Holder.StatusEnum status = holder.getStatus();

        Holder newHolder = new Holder();
        newHolder.setDailyLimit(dailyLimit);
        newHolder.setEmail(email);
        newHolder.setFirstName(firstName);
        newHolder.setLastName(lastName);
        newHolder.setRole(role);
        newHolder.setSalt(salt.toString());
        newHolder.setStatus(status);
        String fullPassword = password + salt;
        newHolder.setPassword(passwordEncoder.encode(fullPassword));
        List<Account> accounts = emptyList();
        newHolder.setAccounts(accounts);
        System.out.println(newHolder.toString());
        return holderRepository.save(newHolder);

//        if(holderRepository.findByEmail(holder.getEmail()) == null) {
//            holder.setPassword(passwordEncoder.encode(holder.getPassword() + holder.getSalt()));
//            holderRepository.save(holder);
//            return holder;
//        } else {
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already in use");
//        }
    }
}
