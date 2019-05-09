package br.com.emanuelvictor.books.domain.resource;

import br.com.emanuelvictor.books.domain.entity.Editora;
import br.com.emanuelvictor.books.domain.repository.IEditoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/editoras")
public class EditoraResource {

    private final IEditoraRepository editoraRepository;

    @GetMapping
    public Page<Editora> listByFilters(final String defaultFilter, final Pageable pageable) {
        return this.editoraRepository.listByFilters(defaultFilter, pageable);
    }

    @GetMapping("/{id}")
    public Optional<Editora> findById(@PathVariable final long id) {
        return this.editoraRepository.findById(id);
    }

    @PostMapping
    public Editora save(@RequestBody final Editora editora) {
        return this.editoraRepository.save(editora);
    }

    @PutMapping("/{id}")
    public Editora save(@PathVariable final long id, @RequestBody final Editora editora) {
        editora.setId(id);
        return this.editoraRepository.save(editora);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        this.editoraRepository.deleteById(id);
    }
}
