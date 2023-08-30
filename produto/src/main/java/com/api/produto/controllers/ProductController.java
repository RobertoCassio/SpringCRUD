package com.api.produto.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.produto.dtos.ProductRecordDTO;
import com.api.produto.model.ProductModel;
import com.api.produto.repositories.ProductRepository;

import jakarta.validation.Valid;

//API Rest- 4 Níveis - 1- Utilize Protocolo HTTP 2-Recursos bem definidos e corretos sos das URI's (Utilização de substantivos)
//3- Utilização dos métodos HTTP de forma semântica (Para salvar o post, para listar o get, para deletar o delete, para atualizar o put)
@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@PostMapping("/products")
	public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDto) { // Response
																												// entity
																												// - Vai
																												// retornar
																												// o
																												// método
																												// saveProduct
																												// do
																												// tipo
																												// ProductModel
		// @RequestBody - Como corpo ele recebe o Dto - sendo um nome e um valor
		// conforme no ProductRecordDTO.java com as validações.
		// O @Valid é para que as validações funcionem aqui.
		var productModel = new ProductModel(); // Vamos receber um productModel, não um DTO, o DTO é como se fosse
												// apenas um modelo.
		BeanUtils.copyProperties(productRecordDto, productModel); // Converte DTO para Model - Método do próprio string
																	// - Recebe o productRecordDto e converte para
																	// productModel
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel)); // Retorna o
																										// response
																										// entity
		// HTTP Status Created para retornar par ao cliente que foi criado -> e no body
		// retorna o que foi salvo.
		// Save também como recurso do spring
	}

	@GetMapping("/products")
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll()); // Get All já implementado
																						// através do find all
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) { // Essa anotação obtém o id quem
																						// vem na URI, dentro de value é
																						// o valor definido na URI, e em
																						// seguida o tipo do mesmo
		Optional<ProductModel> productO = productRepository.findById(id); // Optional pode ou não ser nulo
		if (productO.isEmpty()) { // Caso seja avisa que não foi encontrado
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found.");
		} // Caso não retorna o produto
		return ResponseEntity.status(HttpStatus.OK).body(productO.get());
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid ProductRecordDTO productRecordDTO) {
		Optional<ProductModel> productO = productRepository.findById(id);
		if (productO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
		}
		var productModel = productO.get(); // Atentar-se aqui, essencial não inicial um productModel do 0 já que o id já
		productModel.setUpdated_at(new Date(System.currentTimeMillis()));									// existe
		BeanUtils.copyProperties(productRecordDTO, productModel);
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
		Optional<ProductModel> productO = productRepository.findById(id);
		if (productO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
		}
		productRepository.delete(productO.get());
		return ResponseEntity.status(HttpStatus.OK).body("Product Deleted Sucessfully");
	}
}
