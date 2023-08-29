package org.generation.BlogPessoal.service;

import java.util.List;
import java.util.Optional;

import org.generation.BlogPessoal.model.Postagem;
import org.generation.BlogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostagemService implements CrudService<Postagem>{

	@Autowired
	private PostagemRepository repository;

	public void deleteById(long id) {
		this.repository.deleteById(id);
	}
	
	public List<Postagem> getAll() {
		return this.repository.findAll();
	}
	
	public Optional<Postagem> getById(long id){
		return repository.findById(id);
	}
	
	public Postagem save(Postagem postagem){
		return repository.save(postagem);
	}

}