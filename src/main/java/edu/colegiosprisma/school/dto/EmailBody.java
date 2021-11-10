package edu.colegiosprisma.school.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailBody {
    private String to; // Para: correo
    private String content; // contenido
    private String subject; // asunto

    @Override
    public String toString() {
        return "EmailBody [to=" + to + ", content=" + content + ", subject=" + subject + "]";
    }
}