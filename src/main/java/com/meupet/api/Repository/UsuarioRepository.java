package com.meupet.api.Repository;
import com.meupet.api.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);
}
