package br.unibh.sdm.backend_cliente.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.unibh.sdm.backend_cliente.entidades.Cliente;
import br.unibh.sdm.backend_cliente.persistencia.ClienteRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteTests {

    private static Logger LOGGER = LoggerFactory.getLogger(ClienteTests.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    
	@Autowired
	private ClienteRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Cliente c1 = new Cliente("diego","00000000001","diego@gmail.com","31985281755",df.parse("30/09/2000"),"diego","1234");
		repository.save(c1);

		Cliente c2 = new Cliente("Mathues","00000000002","matheus@gmail.com","31988802742",df.parse("02/12/1995"),"matheus","1234");
		repository.save(c2);

		Cliente c3 = new Cliente("Ricardo","00000000003","ricardo@gmail.com","31987527209",df.parse("13/03/1990"),"ricardo","1234");
		repository.save(c3);
		
		LOGGER.info("Pesquisado todos");
		Iterable<Cliente> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Cliente cliente : lista) {
			LOGGER.info(cliente.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Cliente> result = repository.findByNome("Ricardo");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getEmail(), "ricardo@gmail.com");
		LOGGER.info("Encontrado: {}", result.get(0));
	}
	
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		List<Cliente> result = repository.findByCpf("00000000001");
		for (Cliente cliente : result) {
			LOGGER.info("Excluindo Cliente id = "+cliente.getId());
			repository.delete(cliente);
		}
		result = repository.findByCpf("00000000002");
		for (Cliente cliente : result) {
			LOGGER.info("Excluindo Cliente id = "+cliente.getId());
			repository.delete(cliente);
		}
		result = repository.findByCpf("00000000003");
		for (Cliente cliente : result) {
			LOGGER.info("Excluindo Cliente id = "+cliente.getId());
			repository.delete(cliente);
		}
		result = repository.findByNome("Ricardo");
		assertEquals(result.size(), 0);
		LOGGER.info("Exclus�o feita com sucesso");
	}
	
	
}