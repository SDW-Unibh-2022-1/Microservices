package persistencia;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import entidade.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);
	
	List<Cliente> findByCpf(String cpf);

	Cliente findById(long id);

}