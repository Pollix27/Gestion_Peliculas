package org.uacm.mapeo.ejemplosb0.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.PasswordResetToken;
import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}
