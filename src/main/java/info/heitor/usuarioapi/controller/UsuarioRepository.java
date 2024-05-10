package info.heitor.usuarioapi.controller;

import info.heitor.usuarioapi.model.Usuario;
import org.springframework.data.repository.CrudRepository;

interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}
