package edu.colegiosprisma.school.service.implementation;

import edu.colegiosprisma.school.api.IEmailPort;
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
public class EmailServiceImpl implements IEmailPort {
    // LOGGER: esta variable es para el log de la clase
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender sender; // esta variable es para el envío de correos

    @Override
    public boolean sendEmail(EmailBody emailBody)  { // esta función es para enviar correos
//        LOGGER.info("EmailBody: {}", emailBody.toString());
        return sendEmailTool(emailBody.getContent(), emailBody.getTo(), emailBody.getSubject());
    }

    // sendEmailTool: esta función es para enviar correos
    private boolean sendEmailTool(String textMessage, String email, String subject) {
        boolean isSend = false; // esta variable es para saber si el correo fue enviado
        MimeMessage message = sender.createMimeMessage(); // esta variable es para crear el correo
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email); // esta variable es para el destinatario del correo
            helper.setText(textMessage, true); // esta variable es para el cuerpo del correo.
                                                    // true: para que el cuerpo del correo sea html
            helper.setSubject(subject); // esta variable es para el asunto del correo
            sender.send(message); // esta función es para enviar el correo
            isSend = true; // estableciendo que el correo fue enviado
            LOGGER.info("Mail enviado!"); // correo enviado
        } catch (MessagingException e) {
            LOGGER.error("Hubo un error al enviar el mail: {}", e); // error al enviar el correo
        }
        return isSend;
    }
}