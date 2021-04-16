package org.generation.BlogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.BlogPessoal.model.EntidadeBasica;
import org.generation.BlogPessoal.service.CrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Lembrar de sempre reconsiliar as informacoes no caso de update e tals
 * alternativamente, é sempre interessante sobrescrever os metodos equals e hashcode
 * dessa maneira a aplicacao sabe quando dois Modelos sao "iguais"
 * @author arnaldoidao
 *
 * @param <T>
 */

public class ControllerGenerico<T extends EntidadeBasica> {

	private CrudService<T> service;

	public ControllerGenerico(CrudService<T> service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<T>> GetAll() {
		return ResponseEntity.ok(this.service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<T> getById(@PathVariable long id) {
		return this.service
				.getById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		this.service.deleteById(id);
	}
	
	/**
	 * Recupera o registro da base de dados;
	 * verifica se exite ou nao;
	 * copia as informacoes do parametro para o objeto vindo da base
	 * persiste.
	 * sem o copyProperties, ele duplicara o registro, uma vez que na
	 * @param postagem
	 * @return
	 */
	@PutMapping
	public ResponseEntity<T> put (@RequestBody T postagem){
		Optional<T> tFromDb = this.service.getById(postagem.getId());
		if(!tFromDb.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(postagem, tFromDb);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(this.service.save(postagem));
	}
	
	@PostMapping
	public ResponseEntity<T> post (@RequestBody T postagem){
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(this.service.save(postagem));
	}
}
