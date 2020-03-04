package br.org.pti.api.functional.accountmanager.domain.repositories;

import br.org.pti.api.functional.accountmanager.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 01/01/2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param username String
     * @return Optional<User>
     */
    Optional<User> findByUsername(final String username);

    /**
     * @param username String
     * @param id       Long
     * @return Optional<User>
     */
    Optional<User> findByUsernameAndIdNot(final String username, final Long id);

    /**
     * @param filter
     * @return
     */
    @Query("FROM User u WHERE filter(:filter, u.username) = true")
    Page<User> findByFiltro(final String filter, final Pageable pageable);

}
