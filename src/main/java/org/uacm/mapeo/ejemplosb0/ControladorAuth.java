package org.uacm.mapeo.ejemplosb0;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.uacm.mapeo.ejemplosb0.dto.RegisterRequest;
import org.uacm.mapeo.ejemplosb0.dto.ResetPasswordRequest;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.PasswordResetToken;
import org.uacm.mapeo.ejemplosb0.persistencia.entidades.User;
import org.uacm.mapeo.ejemplosb0.servicios.PasswordResetService;
import org.uacm.mapeo.ejemplosb0.servicios.UserService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ControladorAuth {
    private final UserService userService;
    private final PasswordResetService passwordResetService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "register";
        }
        try {
            User user = userService.registerUser(request.getUsername(), request.getEmail(), request.getPassword());
            return "redirect:/auth/terms?userId=" + user.getId();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/terms")
    public String showTerms(@RequestParam Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "terms";
    }

    @PostMapping("/terms/accept")
    public String acceptTerms(@RequestParam Long userId) {
        userService.acceptTerms(userId);
        return "redirect:/auth/login?registered";
    }

    @GetMapping("/forgot-password")
    public String showForgotPassword() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, Model model) {
        User user = userService.findByEmail(email);
        if (user != null) {
            passwordResetService.createPasswordResetToken(user);
        }
        model.addAttribute("message", "Si el email existe, recibirás un enlace de recuperación");
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetPassword(@RequestParam String token, Model model) {
        PasswordResetToken resetToken = passwordResetService.validateToken(token);
        if (resetToken == null) {
            model.addAttribute("error", "Token inválido o expirado");
            return "reset-password";
        }
        model.addAttribute("token", token);
        model.addAttribute("resetRequest", new ResetPasswordRequest());
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam String token, 
                                      @Valid @ModelAttribute ResetPasswordRequest request,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("token", token);
            return "reset-password";
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            model.addAttribute("token", token);
            return "reset-password";
        }
        PasswordResetToken resetToken = passwordResetService.validateToken(token);
        if (resetToken == null) {
            model.addAttribute("error", "Token inválido o expirado");
            return "reset-password";
        }
        userService.updatePassword(resetToken.getUser(), request.getPassword());
        passwordResetService.markTokenAsUsed(resetToken);
        return "redirect:/auth/login?resetSuccess";
    }
}
