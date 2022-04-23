package br.unibh.sdm.backend_needhelp.entidades;

import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName = "fornecedor")
public class Fornecedor {

	private int id;
	private String nome ;
	private String produto;
	private String endereco;
	private  String contato;
	
	public Fornecedor(int id, String nome, String produto, String endereco, String contato) {
		super();
		this.id = id;
		this.nome = nome;
		this.produto = produto;
		this.endereco = endereco;
		this.contato = contato;
	}

	public Fornecedor() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(contato, endereco, id, nome, produto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(contato, other.contato) && Objects.equals(endereco, other.endereco) && id == other.id
				&& Objects.equals(nome, other.nome) && Objects.equals(produto, other.produto);
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
	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	@DynamoDBAttribute
	public String getEndereco() {
		return endereco;
	}
	

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
		return "Fornecedor [id=" + id + ", nome=" + nome + ", produto=" + produto + ", endereco=" + endereco
				+ ", contato=" + contato + "]";
	}
	
	
	
}
