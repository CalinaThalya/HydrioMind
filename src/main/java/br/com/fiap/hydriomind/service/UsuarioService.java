package br.com.fiap.hydriomind.service;

import br.com.fiap.hydriomind.entity.Usuario;
import br.com.fiap.hydriomind.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tenta encontrar o usuário no banco de dados pelo nome de usuário
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Retorna um objeto UserDetails com as credenciais do usuário
        return User.builder()
                .username(usuario.getUsername())  // Define o nome de usuário
                .password(usuario.getPassword())  // Define a senha criptografada
                .roles("USER")  // Defina os papéis que o usuário pode ter
                .build();
    }

    // Método adicional para buscar o usuário pelo ID
    public Optional<Usuario> encontrarUsuarioPorId(String id) {
        return usuarioRepository.findById(id);
    }

    // Método para deletar um usuário pelo ID
    public void deletarUsuario(String id) {
        usuarioRepository.deleteById(id);
    }

    // Método para encontrar um usuário pelo username
    public Optional<Usuario> encontrarUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
