package info.heitor.usuarioapi.controller;

import info.heitor.usuarioapi.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        usuarioRepository.saveAll(List.of(
                new Usuario("Heitor","heitor@infnet","123456789"),
                new Usuario("Maria","maria@infnet","123456789"),
                new Usuario("Lucas","lucas@infnet","123456789")
        ));
    }

    @GetMapping("/usuarios")
    Iterable<Usuario> usuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/usuarios")
    ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        if(!usuario.getNome().isEmpty() && !usuario.getSenha().isEmpty() && !usuario.getEmail().isEmpty()){
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/usuarios/{id}")
    ResponseEntity<Usuario> deleteUsuario(@PathVariable int id){
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/usuarios/{id}")
    Usuario updateUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        if (usuarioRepository.existsById(id)){
            Optional<Usuario> usuarioAtual = usuarioRepository.findById(id);
            if(usuarioAtual.isPresent()){
                usuario.setId(id);
                usuarioRepository.save(usuario);
            }
        }
        return usuario;
    }

}
