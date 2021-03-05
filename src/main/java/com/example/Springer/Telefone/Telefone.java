package com.example.Springer.Telefone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Telephone_Table") //Usado Telefone_Table para fins de diferenciação.
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long num_Tel;
	
	//Criação de colunas dentro da tabela
	
	@Column(name="NUMERO_TEL")
	private String numero;
	
	@Column(name="DDD_TEL")
	private String ddd;
	
	@Column(name="USER_TEL")
	private String usuario;
	
	public Telefone() {
		super();
	}

	public long getNum_Tel() {
		return num_Tel;
	}

	public void setNum_Tel(long num_Tel) {
		this.num_Tel = num_Tel;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


}