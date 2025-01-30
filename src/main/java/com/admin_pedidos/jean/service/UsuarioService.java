package com.admin_pedidos.jean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Usuario;
import com.admin_pedidos.jean.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(int id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(int id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId_usuario(id);
            return usuarioRepository.save(usuario);
        }
        throw new RuntimeException("Usuario not found");
    }

    public void deleteById(int id) {
        usuarioRepository.deleteById(id);
    }
    public Usuario autenticarUsuario(String usuario, String clave) {
        return usuarioRepository.findByNomusuarioAndClaveusuario(usuario, clave);
    }
}