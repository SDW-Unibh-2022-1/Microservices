package br.unibh.sdm.backend_needhelp.entidades;

import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "cliente")
public class Cliente {
	
		private int id;
		private String nome;
		private String cpf;
		private String contato;
		
		public Cliente() {
			super();
		}

		public Cliente(int id, String nome, String cpf, String contato) {
			super();
			this.id = id;
			this.nome = nome;
			this.cpf = cpf;
			this.contato = contato;
		}

		

		@Override
		public int hashCode() {
			return Objects.hash(contato, cpf, id, nome);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cliente other = (Cliente) obj;
			return Objects.equals(contato, other.contato) && Objects.equals(cpf, other.cpf) && id == other.id
					&& Objects.equals(nome, other.nome);
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
		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
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
			return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
		}
		
		
		

}
