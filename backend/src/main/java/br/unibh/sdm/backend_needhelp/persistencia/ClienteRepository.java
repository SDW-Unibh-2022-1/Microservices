package br.unibh.sdm.backend_needhelp.persistencia;


import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_needhelp.entidades.Cliente;

@EnableScan()
public interface ClienteRepository extends CrudRepository<Cliente, String> {
	
	List<Cliente> findByNome(String nome);

	Iterable<Cliente> findById(int id);

	void deleteById(int id);

	
}