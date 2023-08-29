package org.generation.BlogPessoal.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
	void deleteById(long id);
	List<T> getAll();
	Optional<T> getById(long id);
	T save(T t);
}
