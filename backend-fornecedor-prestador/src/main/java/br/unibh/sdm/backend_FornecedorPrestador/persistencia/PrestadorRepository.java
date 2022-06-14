package br.unibh.sdm.backend_FornecedorPrestador.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_FornecedorPrestador.entidades.PrestadorServicos;

@EnableScan()
public interface PrestadorRepository extends CrudRepository<PrestadorServicos, String> {
	
	List<PrestadorServicos> findByNome(String nome);

	Iterable<PrestadorServicos> findById(int id);

	void deleteById(int id);

		
}