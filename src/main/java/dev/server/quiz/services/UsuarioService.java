package dev.server.quiz.services;

import dev.server.quiz.entities.Usuario;
import dev.server.quiz.repositories.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements DAOService<Usuario>{

    private final UsuarioRepo repo;

    public UsuarioService(UsuarioRepo repo) {
        this.repo = repo;
    }

    @Override
    public Usuario registrar(Usuario p) throws Exception {
        return repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        repo.deleteById(id);
    }

    @Override
    public Usuario obtener(Long id) throws Exception {
        return repo.findById(id).get();
    }

    @Override
    public List<Usuario> listar() throws Exception {
        return repo.findAll();
    }

    public Usuario obtenerPorUsername(String username) throws Exception {
        return repo.findByUsername(username);
    }
}
