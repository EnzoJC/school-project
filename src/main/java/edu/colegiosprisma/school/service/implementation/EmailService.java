package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.api.EmailPort;
import edu.colegiosprisma.school.dto.EmailBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender sender;

    @Override
    public boolean sendEmail(EmailBody emailBody)  {
        LOGGER.info("EmailBody: {}", emailBody.toString());
        return sendEmailTool(emailBody.getContent(), emailBody.getEmail(), emailBody.getSubject());
    }

    private boolean sendEmailTool(String textMessage, String email, String subject) {
        boolean isSend = false;
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email); // establecer a quien se envia
            helper.setText(textMessage, true); // establecer el mensaje con HTML activado
            helper.setSubject(subject); // establecer el asunto del mensaje
            sender.send(message); // enviar el correo
            isSend = true; // cambiar estado a verdadero
            LOGGER.info("Mail enviado!");
        } catch (MessagingException e) {
            LOGGER.error("Hubo un error al enviar el mail: {}", e);
        }
        return isSend;
    }



}