package org.generation.BlogPessoal.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//parametros que definem comportamentos
@Entity
@Table(name = "postagem") //essa entidade vai virar uma tabela
public class Postagem 
{	
	//id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //coloando o id como pk no bd
	private long id;
	
	//titulo
	@NotNull
	@Size(min = 5, max = 100)
	private String titulo;
	
	//texto
	@NotNull
	@Size(min = 10, max = 500)
	private String texto;
	
	//determinar o tempo
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis()); //verificar exatamente a data que foi feito
	
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
	
	
	
}