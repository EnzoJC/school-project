package edu.colegiosprisma.school.api;

import edu.colegiosprisma.school.dto.EmailBody;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public interface EmailPort {
    public boolean sendEmail(EmailBody emailBody);
}
