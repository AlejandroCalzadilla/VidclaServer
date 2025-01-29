package org.mailgrupo13.vidcla.usuarios.usuario.services;

import org.mailgrupo13.vidcla.usuarios.usuario.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
