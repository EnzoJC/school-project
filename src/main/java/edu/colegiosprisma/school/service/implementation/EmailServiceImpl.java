package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.entity.Parent;
import edu.colegiosprisma.school.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {
    private final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String send(Parent parent, String templateEngine) {
        Context context = new Context();
        context.setVariable("parent", parent);
        String process = this.templateEngine.process(templateEngine, context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject("Registro de Matrícula - Colegios Prisma");
            helper.setText(process, true);
            helper.setTo(parent.getEmail());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.info("Email", e.toString());
        }
        return "enviado";
    }
}