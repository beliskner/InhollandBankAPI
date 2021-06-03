package io.swagger;

import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseAccount;
import io.swagger.repository.AccountsRepo;
import io.swagger.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.service.Holders.HolderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.event.EventListener;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.Arrays;

@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" , "io.swagger.configuration", "io.swagger.helpers"})
public class Swagger2SpringBoot implements CommandLineRunner {

    @Autowired
    AccountsService accountsService;

    @Autowired
    private ApplicationContext appContext;




    @Autowired
    HolderService holderService;

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }

        String[] beans = appContext.getBeanDefinitionNames();
        Arrays.sort(beans);
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        holderService.addInitialHolders();
//        accountsService.addAccountForBank();
//        accountsService.addAccountForCustomer();
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
