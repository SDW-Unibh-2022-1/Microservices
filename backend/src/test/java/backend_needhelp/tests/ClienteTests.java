package backend_needhelp.tests;



import static org.junit.Assert.assertNotNull;

import java.text.ParseException;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import br.unibh.sdm.backend_needhelp.entidades.Cliente;
import br.unibh.sdm.backend_needhelp.persistencia.ClienteRepository;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, ClienteTests.DynamoDBConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ClienteTests {

	private static Logger LOGGER = LoggerFactory.getLogger(ClienteTests.class);

	@Configuration
	@EnableDynamoDBRepositories(basePackageClasses = { ClienteRepository.class })
	public static class DynamoDBConfig {

		@Value("${amazon.aws.accesskey}")
		private String amazonAWSAccessKey;

		@Value("${amazon.aws.secretkey}")
		private String amazonAWSSecretKey;

		public AWSCredentialsProvider amazonAWSCredentialsProvider() {
			return new AWSStaticCredentialsProvider(amazonAWSCredentials());
		}

		@Bean
		public AWSCredentials amazonAWSCredentials() {
			return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
		}

		@Bean
		public AmazonDynamoDB amazonDynamoDB() {
			return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
					.withRegion(Regions.US_EAST_1).build();
		}
	}

	@Autowired
	private ClienteRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");

		Cliente c1 = new Cliente( "Rodrigo", "1388408041", "(31)9839698670");
		Cliente c2 = new Cliente( "Matheus", "46801087080", "(31)7481851120");
		Cliente c3 = new Cliente( "Diego", "8044805005", "(31)996245524520");

		repository.save(c1);
		repository.save(c2);
		repository.save(c3);

		Iterable<Cliente> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Cliente cliente : lista) {
			LOGGER.info(cliente.toString());
		}
	}
//		LOGGER.info("Pesquisado um objeto");
//		List<Cliente> result = repository.findByCpf("1388408041");
//		assertEquals(result.size(), 7);
//		LOGGER.info("Encontrado: {}", result.size());
//	}

//	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		
		Cliente c1 = new Cliente( "Rodrigo", "1388408041", "(31)9839698670");
		Cliente c2 = new Cliente( "Matheus", "46801087080", "(31)7481851120");
		Cliente c3 = new Cliente( "Diego", "8044805005", "(31)996245524520");
		repository.delete(c1);
		repository.delete(c2);
		repository.delete(c3);
		//assertEquals(result.size(), 0);
		LOGGER.info("Exclusão feita com sucesso");
	}

}
