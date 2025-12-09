package org.uacm.mapeo.ejemplosb0.servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Recuperación de Contraseña");
        message.setText("Para restablecer tu contraseña, haz clic en el siguiente enlace:\n\n" +
                "http://localhost:8080/auth/reset-password?token=" + token +
                "\n\nEste enlace expirará en 24 horas.");
        mailSender.send(message);
    }
}
