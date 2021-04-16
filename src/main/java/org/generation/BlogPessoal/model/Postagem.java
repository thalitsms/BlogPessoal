package org.generation.BlogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//parametros que definem comportamentos
@Entity
@Table(name = "postagem") // essa entidade vai virar uma tabela
public class Postagem implements EntidadeBasica {
	// id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // coloando o id como pk no bd
	private long id;

	// titulo
	@NotNull
	@Size(min = 5, max = 100)
	private String titulo;

	// texto
	@NotNull
	@Size(min = 10, max = 500)
	private String texto;

	// determinar o tempo
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis()); // verificar exatamente a data que foi feito

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postagem other = (Postagem) obj;
		if (id != other.id)
			return false;
		return true;
	}

}