package br.com.emanuelvictor.books.domain.repository;

import br.com.emanuelvictor.books.domain.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILivroRepository extends JpaRepository<Livro, Long> {


}
