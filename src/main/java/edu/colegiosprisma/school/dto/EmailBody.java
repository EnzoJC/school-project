package edu.colegiosprisma.school.dto;

import lombok.Data;

@Data
public class EmailBody {
    private String email; // correo
    private String content; // contenido
    private String subject; // asunto

    @Override
    public String toString() {
        return "EmailBody [email=" + email + ", content=" + content + ", subject=" + subject + "]";
    }
}