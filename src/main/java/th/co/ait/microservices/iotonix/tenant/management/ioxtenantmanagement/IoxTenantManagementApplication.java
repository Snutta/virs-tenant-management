package th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication( scanBasePackages =   { "th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement" } )
@EnableTransactionManagement
@EnableJpaRepositories( basePackages = {"th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.repository"  })
@EntityScan( basePackages = { "th.co.ait.microservices.iotonix.tenant.management.ioxtenantmanagement.jpa.model" }  )
public class IoxTenantManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(IoxTenantManagementApplication.class, args);
    }

    @Bean
    public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder  encoder(){
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder(11);
        return encode;
    }
}
