package br.org.pti.api.functional.portalcompras.domain.repository;

import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     *
     */
    @Query("SELECT new Usuario(" +
            "       usuario.id, " +
            "       usuario.nome, " +
            "       usuario.documento, " +
            "       usuario.email, " +
            "       usuario.ativo, " +
            "       usuario.administrador," +
            "       usuario.senha," +
            "       MAX(usuario.email) as username, " +
            "       usuario.tentativasLogin, " +
            "       usuario.ultimaTentativaLogin," +
            "       grupoAcesso, " +
            "       usuario.ultimoAcesso, " +
            "       usuario.codigo," +
            "       usuario.interno," +
            "       fornecedor" +
            ") FROM Usuario usuario " +
            "       LEFT OUTER JOIN GrupoAcesso grupoAcesso ON grupoAcesso.id = usuario.grupoAcesso.id " +
            "       LEFT OUTER JOIN Fornecedor fornecedor ON fornecedor.usuario.id = usuario.id " +
            " WHERE ( ( FILTER(:filter, usuario.nome, usuario.email ) = TRUE ) AND ( usuario.ativo = :ativo OR :ativo = NULL ) )" +
            "   GROUP BY    fornecedor.id, usuario.id, usuario.nome, usuario.documento, usuario.email, usuario.ativo, " +
            "               usuario.administrador, usuario.senha, usuario.tentativasLogin, usuario.ultimaTentativaLogin," +
            "               usuario.ultimoAcesso, usuario.codigo, grupoAcesso")
    Page<Usuario> listByFilters(@Param("filter") final String filter, @Param("ativo") final Boolean ativo, final Pageable pageable);

    /**
     * @param username String
     * @return UserDetails
     */
    @Query(" FROM Usuario usuario WHERE ( LOWER(usuario.email) = LOWER(:username) OR usuario.documento = :username )")
    UserDetails findByEmailIgnoreCaseOrDocumento(@Param("username") final String username);

    /**
     * @param codigo String
     * @return Usuario
     */
    Usuario findByCodigo(final String codigo);

    @Query("FROM Usuario us " +
            "WHERE UPPER(us.nome) LIKE CONCAT('%', UPPER(:filtro), '%') " +
            "OR UPPER(us.codigo) LIKE CONCAT('%', UPPER(:filtro), '%') " +
            "OR UPPER(us.email) LIKE CONCAT('%', UPPER(:filtro), '%')")
    List<Usuario> findUsuariosPor(String filtro);

    /**
     *
     * @param email
     * @return
     */
    Optional<Usuario> findByEmail(String email);
}
