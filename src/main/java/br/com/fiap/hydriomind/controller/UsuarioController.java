package br.com.fiap.hydriomind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.fiap.hydriomind.entity.Usuario;
import br.com.fiap.hydriomind.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Mostra o formulário de cadastro
    @GetMapping("/req/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "signup"; // Retorna signup.html
    }

    // Realiza o registro do usuário
    @PostMapping("/req/signup")
    public String registerUser(@Validated Usuario usuario, BindingResult result, Model model) {
        // Verifica se houve algum erro de validação no formulário
        if (result.hasErrors()) {
            return "signup"; // Retorna ao formulário com os erros de validação
        }

        // Verifica se o usuário já existe
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            model.addAttribute("error", "O nome de usuário já está em uso.");
            return "signup"; // Retorna para o formulário com erro
        }

        // Criptografa a senha antes de salvar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Salva o usuário no banco de dados
        usuarioRepository.save(usuario);

        // Redireciona para o login
        return "redirect:/req/login";
    }

    // Mostra o formulário de login
    @GetMapping("/req/login")
    public String showLoginForm() {
        return "login"; // Retorna login.html
    }

    @GetMapping("/home")
    public String home(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username); // Passa o nome de usuário para a página
        return "home"; // Retorna home.html
    }
}
