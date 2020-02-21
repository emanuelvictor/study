package br.org.pti.compras.domain.repository;

import br.org.pti.compras.domain.entity.fornecedor.CategoriaFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaFornecedorRepository extends JpaRepository<CategoriaFornecedor, Long> {

    @Query("FROM CategoriaFornecedor categoriaFornecedor WHERE" +
            "   (" +
            "       categoriaFornecedor.fornecedor.id = :fornecedorId AND categoriaFornecedor.categoria.id = :categoriaId" +
            "   )"
    )
    CategoriaFornecedor findByFornecedorIdAndCategoriaId(@Param("fornecedorId") long fornecedorId, @Param("categoriaId") long categoriaId);

}
