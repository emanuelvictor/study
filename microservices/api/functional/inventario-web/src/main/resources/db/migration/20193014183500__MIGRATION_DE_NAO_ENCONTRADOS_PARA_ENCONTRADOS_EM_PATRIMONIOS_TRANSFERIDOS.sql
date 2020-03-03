UPDATE patrimonio.patrimonio SET encontrado = true
WHERE patrimonio.id IN (SELECT patrimonio.id FROM patrimonio.patrimonio
                                                      INNER JOIN patrimonio.centro_custo_inventario ON centro_custo_inventario.id = centro_custo_inventario_id
                        WHERE centro_custo_anterior_id IS NOT NULL AND centro_custo_inventario.centro_custo_id != centro_custo_anterior_id)
