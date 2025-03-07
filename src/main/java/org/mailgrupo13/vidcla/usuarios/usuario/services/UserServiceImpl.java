package org.mailgrupo13.vidcla.usuarios.usuario.services;

import org.mailgrupo13.vidcla.usuarios.usuario.Role;
import org.mailgrupo13.vidcla.usuarios.usuario.RoleRepository;
import org.mailgrupo13.vidcla.usuarios.usuario.User;
import org.mailgrupo13.vidcla.usuarios.usuario.UserRepository;
import org.mailgrupo13.vidcla.validations.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }




    @Override
    @Transactional
    public User save(User user) {

        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);


        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }


    @Override
    @Transactional
    public User update(Long id, User userDetails) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User no encontrado con  " + id));

        user.setUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setEnabled(userDetails.isEnabled());

        List<Role> roles = new ArrayList<>();
        roleRepository.findByName("ROLE_USER").ifPresent(roles::add);

        if (userDetails.isAdmin()) {
            roleRepository.findByName("ROLE_ADMIN").ifPresent(roles::add);
        }

        user.setRoles(roles);

        return repository.save(user);
    }







    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }






}
