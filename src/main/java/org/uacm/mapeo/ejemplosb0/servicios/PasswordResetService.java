package org.uacm.mapeo.ejemplosb0.servicios;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.PasswordResetToken;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.User;
import org.uacm.mapeo.ejemplosb0.persistencia.repositorios.PasswordResetTokenRepository;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;

    public void createPasswordResetToken(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(24));
        tokenRepository.save(resetToken);
        emailService.sendPasswordResetEmail(user.getEmail(), token);
    }

    public PasswordResetToken validateToken(String token) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token).orElse(null);
        if (resetToken == null || resetToken.getUsed() || 
            resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return null;
        }
        return resetToken;
    }

    public void markTokenAsUsed(PasswordResetToken token) {
        token.setUsed(true);
        tokenRepository.save(token);
    }
}
