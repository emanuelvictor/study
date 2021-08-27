package br.com.emanuelvictor.books.domain.repository;

import br.com.emanuelvictor.books.domain.entity.Editora;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEditoraRepository extends JpaRepository<Editora, Long> {

    @Query("FROM Editora editora WHERE" +
            "   (   " +
            "       FILTER(:filter, editora.nome) = TRUE" +
            "   )"
    )
    Page<Editora> listByFilters(@Param("filter") final String defaultFilter, final Pageable pageable);
}
