package br.com.emanuelvictor.books.domain.resource;

import br.com.emanuelvictor.books.domain.repository.IEditoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("editoras")
public class EditoraResource {

    private final IEditoraRepository editoraRepository;

}
