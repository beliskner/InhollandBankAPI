package io.swagger;

import io.swagger.model.Account;
import io.swagger.model.BaseModels.BaseAccount;
import io.swagger.repository.AccountsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.Arrays;

@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" , "io.swagger.configuration", "io.swagger.helpers"})
public class Swagger2SpringBoot implements CommandLineRunner {

    @Autowired
    AccountsRepo accountsRepo;

    @Autowired
    private ApplicationContext appContext;




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

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }


}
