package io.swagger.service.Holders;

import io.swagger.model.Account;
import io.swagger.model.DTO.HolderDTO.BodyDailyLimit;
import io.swagger.model.DTO.HolderDTO.RequestBodyHolder;
import io.swagger.model.DTO.HolderDTO.RequestBodyUpdateHolder;
import io.swagger.model.Enums.IncludeFrozen;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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

    public void addInitialHolders() {
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

        Holder holder3 = new Holder();
        holder3.setDailyLimit(new BigDecimal("250"));
        holder3.setEmail("martin@stok.com");
        holder3.setFirstName("martin");
        holder3.setLastName("stok");
        holder3.setRole(Role.ROLE_CUSTOMER);
        holder3.setSalt(salt.toString());
        holder3.setStatus(Holder.StatusEnum.FROZEN);
        holder3.setPassword(passwordEncoder.encode(fullPassword));
        holder3.setAccounts(accounts);
        holderRepository.save(holder3);

        Holder holder4 = new Holder();
        holder4.setDailyLimit(new BigDecimal("250"));
        holder4.setEmail("willem@kooiman.com");
        holder4.setFirstName("willem");
        holder4.setLastName("kooiman");
        holder4.setRole(Role.ROLE_CUSTOMER);
        holder4.setSalt(salt.toString());
        holder4.setStatus(Holder.StatusEnum.FROZEN);
        holder4.setPassword(passwordEncoder.encode(fullPassword));
        holder4.setAccounts(accounts);
        holderRepository.save(holder4);

        Holder holder5 = new Holder();
        holder5.setDailyLimit(new BigDecimal("10000"));
        holder5.setEmail("test@inholland.nl");
        holder5.setFirstName("Test");
        holder5.setLastName("Account");
        holder5.setRole(Role.ROLE_EMPLOYEE);
        holder5.setSalt(salt.toString());
        holder5.setStatus(Holder.StatusEnum.ACTIVE);
        holder5.setPassword(passwordEncoder.encode(fullPassword));
        holder5.setAccounts(accounts);
        holderRepository.save(holder5);
    }

    public Holder add(@Valid @RequestBody RequestBodyHolder body) {
        if(holderRepository.findByEmail(body.getEmail()) == null) {
            // create random salt
            Random r = new SecureRandom();
            byte[] salt = new byte[20];
            r.nextBytes(salt);

            // read request body
            BigDecimal dailyLimit = body.getDailyLimit();
            String email = body.getEmail();
            String firstName = body.getFirstName();
            String lastName = body.getLastName();
            Role role = body.getRole();
            String password = body.getPassword();
            Holder.StatusEnum status = Holder.StatusEnum.ACTIVE;

            // create new holder
            Holder holder = new Holder();
            holder.setDailyLimit(dailyLimit);
            holder.setEmail(email);
            holder.setFirstName(firstName);
            holder.setLastName(lastName);
            holder.setRole(role);
            holder.setSalt(salt.toString());
            holder.setStatus(status);
            String fullPassword = password + salt;
            holder.setPassword(passwordEncoder.encode(fullPassword));
            List<Account> accounts = emptyList();
            holder.setAccounts(accounts);
            return holderRepository.save(holder);
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already in use");
        }
    }

    public Holder getHolderById(int id) {

        Holder holder = holderRepository.findById(id);

        return holder;
    }

    public Holder getHolderByEmail(String email) {
        Holder holder = holderRepository.findByEmail(email);

        return holder;
    }

    public List<Holder> getAllHolders(Role role, String firstName, String lastName, IncludeFrozen includeFrozen) {
        Holder.StatusEnum status = null;
        if(includeFrozen != IncludeFrozen.YES) {
            // if includeFrozen is no or empty (not Yes), where status is active
            status = Holder.StatusEnum.ACTIVE;
        } // if includeFrozen is yes. no where query for status. status stays null

        // If first and lastName are empty set them to empty string for query. otherwise set to lowercase for case insensitive
        firstName = firstName == null ? "" : firstName.toLowerCase();
        lastName = lastName == null ? "" : lastName.toLowerCase();

        List<Holder> holders = emptyList();
        if (role != null && status != null) {
            holders = holderRepository.searchAllHoldersByRoleAndStatus(role, firstName, lastName, status);
        } else if(role != null && status == null) {
            holders = holderRepository.searchAllHoldersByRole(role, firstName, lastName);
        } else if(role == null && status != null) {
            holders = holderRepository.searchAllHoldersByStatus(firstName, lastName, status);
        } else {
            // both role and status are null
            holders = holderRepository.searchAllHolders(firstName, lastName);
        }

        return holders;
    }

    public List<Account> getAccountsByHolderId(int id, String includeClosed) {
        List<Account> accounts;
        // Todo: accountsService has no function yet to filter on closed for a holder. So this will change
        accounts = accountsService.getAllAccountsByHolderId(id);
        return accounts;
    }

    public Holder updateDailyLimitByHolderId(int id, BigDecimal dailyLimit) {
        Holder holder = getHolderById(id);
        holder.setDailyLimit(dailyLimit);
        return holderRepository.save(holder);
    }

    public Holder updateHolderStatusByHolderId(int id, Holder.StatusEnum status) {
        Holder holder = getHolderById(id);
        holder.setStatus(status);
        return holderRepository.save(holder);
    }

    public Holder updateHolderByHolderId(int id, RequestBodyUpdateHolder body) {
        Holder holder = getHolderById(id);
        System.out.println(holder);
        System.out.println(body.getFirstName());
        holder.setFirstName(body.getFirstName());
        holder.setLastName(body.getLastName());
        holder.setEmail(body.getEmail());
        holder.setRole(body.getRole());
        holder.setDailyLimit(body.getDailyLimit());
        return holderRepository.save(holder);
    }

    public Holder deleteHolderById(int id) {
        Holder holder = getHolderById(id);
        holder.setStatus(Holder.StatusEnum.FROZEN);
        return holder;
    }
}
