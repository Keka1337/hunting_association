package bg.fon.huntingassociation.repository;

import bg.fon.huntingassociation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository  extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    boolean existsByEmail(String email);

    boolean existsByJmbg(String jmbg);
}
