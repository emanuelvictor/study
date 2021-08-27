package br.com.emanuelvictor.books.domain.repository;

import br.com.emanuelvictor.books.domain.entity.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ILivroRepository extends JpaRepository<Livro, Long> {

    @Query("FROM Livro livro WHERE" +
            "   (   " +
            "       FILTER(:filter, livro.nome, livro.editora.nome) = TRUE" +
            "   )"
    )
    Page<Livro> listByFilters(@Param("filter") final String defaultFilter, final Pageable pageable);
}
