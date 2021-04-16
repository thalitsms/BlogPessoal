package org.generation.BlogPessoal.controller;

import java.util.List;

import org.generation.BlogPessoal.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ControllerGenerico<T> {

	private CrudService<T> service;

	public ControllerGenerico(CrudService<T> service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<T>> GetAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<T> getById(@PathVariable long id) {
		return service
				.getById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		service.deleteById(id);
	}
	
	@PutMapping
	public ResponseEntity<T> put (@RequestBody T postagem){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(service.save(postagem));
	}
	
	@PostMapping
	public ResponseEntity<T> post (@RequestBody T postagem){
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(service.save(postagem));
	}
}
