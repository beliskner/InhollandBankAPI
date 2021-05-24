package io.swagger;

import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseAccount;
import io.swagger.repository.AccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import io.swagger.api.HoldersController.HoldersApiController;
import io.swagger.model.Enums.Role;
import io.swagger.model.Holder;
import io.swagger.repository.HolderRepository;
import io.swagger.service.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.Arrays;

import java.math.BigDecimal;

@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" , "io.swagger.configuration", "io.swagger.helpers"})
public class Swagger2SpringBoot implements CommandLineRunner {

    @Autowired
    AccountsRepo accountsRepo;

    @Autowired
    private ApplicationContext appContext;




    @Autowired
    HolderService holderService;

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }

        //iban is generated in accounts -> ibangenerator
        Account bank = new Account();
        bank.setStatus(BaseAccount.StatusEnum.OPEN);
        accountsRepo.save(bank);


        String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
//        for (String bean : beans) {
//            System.out.println(bean);
//        }




    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        // Add holders to test API with
        holderService.addInitialHolders();
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
