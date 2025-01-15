package tn.esprit.gamepi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gamepi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
