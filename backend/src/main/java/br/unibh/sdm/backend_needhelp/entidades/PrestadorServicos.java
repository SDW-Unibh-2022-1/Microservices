package br.unibh.sdm.backend_needhelp.entidades;

import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "prestador")
public class PrestadorServicos {
	
	private int id;
	private String nome ;

	private String contato;
	private String servico;
	public PrestadorServicos(int id, String nome, String servico, String contato) {
		super();
		this.id = id;
		this.nome = nome;
		this.servico = servico;
		this.contato = contato;
	}

	public PrestadorServicos() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(contato, id, nome, servico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestadorServicos other = (PrestadorServicos) obj;
		return Objects.equals(contato, other.contato) && id == other.id && Objects.equals(nome, other.nome)
				&& servico == other.servico;
	}

	@DynamoDBHashKey
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@DynamoDBAttribute
	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	@DynamoDBAttribute
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	@Override
	public String toString() {
		return "PrestadorServicos [id=" + id + ", nome=" + nome + ", servico=" + servico + ", contato=" + contato + "]";
	}
	
	
	
}
