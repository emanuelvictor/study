package br.com.emanuelvictor.books.domain.resource;

import br.com.emanuelvictor.books.domain.repository.ILivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("livros")
public class LivroResource {

    private final ILivroRepository livroRepository;

}
