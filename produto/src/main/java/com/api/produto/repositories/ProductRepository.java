package com.api.produto.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.produto.model.ProductModel;

//Mostrar que a classe vai ser um bin gerenciado pelo spring | Preciso descobrir o que é um bin

@Repository //Explicita que vai ser um bin gerenciado pelo Spring
public interface ProductRepository extends JpaRepository<ProductModel, UUID> { //Entidade que o repositório contempla - ProductModel | Tipo de Identificador: UUID;
	

}
