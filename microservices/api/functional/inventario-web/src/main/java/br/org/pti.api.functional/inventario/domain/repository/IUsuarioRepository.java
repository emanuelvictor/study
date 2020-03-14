package br.org.pti.api.functional.inventario.domain.repository;

import br.org.pti.api.functional.inventario.domain.entity.configuracao.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * @param filter   String
     * @param ativo    Boolean
     * @param pageable Pageable
     * @return Page<Usuario></Usuario>
     */
    @Query("SELECT new Usuario(" +
            "       usuario.id, " +
            "       usuario.nome, " +
            "       usuario.documento, " +
            "       usuario.email, " +
            "       usuario.ativo, " +
            "       usuario.root," +
            "       usuario.senha," +
            "       MAX(usuario.email) as username, " +
            "       usuario.tentativasLogin, " +
            "       usuario.ultimaTentativaLogin," +
            "       grupoAcesso, " +
            "       usuario.ultimoAcesso, " +
            "       usuario.codigo," +
            "       usuario.interno" +
            ") FROM Usuario usuario " +
            "       LEFT OUTER JOIN GrupoAcesso grupoAcesso ON grupoAcesso.id = usuario.grupoAcesso.id " +
            " WHERE ( ( FILTER(:filter, usuario.nome, usuario.email ) = TRUE ) AND ( usuario.ativo = :ativo OR :ativo = NULL ) )" +
            "   GROUP BY    usuario.id, usuario.nome, usuario.documento, usuario.email, usuario.ativo, " +
            "               usuario.root, usuario.senha, usuario.tentativasLogin, usuario.ultimaTentativaLogin," +
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

    /**
     * @param filtro String
     * @return List<Usuario></Usuario>
     */
    @Query("FROM Usuario us " +
            "WHERE UPPER(us.nome) LIKE CONCAT('%', UPPER(:filtro), '%') " +
            "OR UPPER(us.codigo) LIKE CONCAT('%', UPPER(:filtro), '%') " +
            "OR UPPER(us.email) LIKE CONCAT('%', UPPER(:filtro), '%')")
    List<Usuario> findUsuariosPor(String filtro);

}