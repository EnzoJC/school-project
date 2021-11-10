package edu.colegiosprisma.school.api;

import edu.colegiosprisma.school.dto.EmailBody;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @Configuration: marca esta clase como configuración de Spring
@Configuration
// @EnableAutoConfiguration: habilita la configuración automática de Spring
@EnableAutoConfiguration
// @ComponentScan: habilita la búsqueda de componentes de Spring
@ComponentScan
public interface IEmailPort {
    public boolean sendEmail(EmailBody emailBody);
}
