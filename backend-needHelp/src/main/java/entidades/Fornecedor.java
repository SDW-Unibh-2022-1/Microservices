package entidades;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "fornecedor")
public class Fornecedor {
	
	private int  id;
	private String  nome;
	private TipoTrabalho tipoTrabalho;
	public Fornecedor(int id, String nome, TipoTrabalho tipoTrabalho) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipoTrabalho = tipoTrabalho;
	}
	public Fornecedor() {
		super();
	}
	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", nome=" + nome + ", tipoTrabalho=" + tipoTrabalho + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipoTrabalho == null) ? 0 : tipoTrabalho.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipoTrabalho == null) {
			if (other.tipoTrabalho != null)
				return false;
		} else if (!tipoTrabalho.equals(other.tipoTrabalho))
			return false;
		return true;
	}
	
	@DynamoDBHashKey
	@DynamoDBAutoGeneratedKey
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
	public TipoTrabalho getTipoTrabalho() {
		return tipoTrabalho;
	}
	public void setTipoTrabalho(TipoTrabalho tipoTrabalho) {
		this.tipoTrabalho = tipoTrabalho;
	}
	
	

}
