package br.com.emanuelvictor.books.domain.resource;

import br.com.emanuelvictor.books.domain.entity.Livro;
import br.com.emanuelvictor.books.domain.repository.ILivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("livros")
public class LivroResource {

    private final ILivroRepository livroRepository;

    @GetMapping
    public Page<Livro> listByFilters(final String defaultFilter, final Boolean ativoFilter, final Pageable pageable) {
//        return this.livroRepository.listByFilters(defaultFilter, ativoFilter, pageable);
        return null;
    }

    @GetMapping("/{id}")
    public Optional<Livro> findById(@PathVariable final long id) {
        return this.livroRepository.findById(id);
    }

    @PostMapping
    public Livro save(@RequestBody final Livro livro) {
        return this.livroRepository.save(livro);
    }

    @PutMapping("/{id}")
    public Livro save(@PathVariable final long id, @RequestBody final Livro livro) {
        livro.setId(id);
        return this.livroRepository.save(livro);
    }

}
