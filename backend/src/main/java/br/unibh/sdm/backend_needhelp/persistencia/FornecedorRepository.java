package br.unibh.sdm.backend_needhelp.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_needhelp.entidades.Fornecedor;

@EnableScan()
public interface FornecedorRepository extends CrudRepository<Fornecedor, String> {
	
	List<Fornecedor> findByNome(String nome);

	Iterable<Fornecedor> findById(int id);
	
}