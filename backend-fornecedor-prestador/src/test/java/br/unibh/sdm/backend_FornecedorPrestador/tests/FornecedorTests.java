package br.unibh.sdm.backend_FornecedorPrestador.tests;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import java.util.List;

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

import br.unibh.sdm.backend_FornecedorPrestador.entidades.Fornecedor;
import br.unibh.sdm.backend_FornecedorPrestador.persistencia.FornecedorRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, FornecedorTests.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FornecedorTests {

	private static Logger LOGGER = LoggerFactory.getLogger(FornecedorTests.class);

	@Configuration
	@EnableDynamoDBRepositories(basePackageClasses = { FornecedorRepository.class })
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
	private FornecedorRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");

		Fornecedor f1 = new Fornecedor(1, "tintas", "tinta", "rua marataizes n�21","tintas@gmail.com");
		Fornecedor f2 = new Fornecedor(2, "Testeja", "Piso", "rua colehos n�1565","testeja@gmail.com");

		repository.save(f1);
		repository.save(f2);
		;

		Iterable<Fornecedor> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Fornecedor fornecedor : lista) {
			LOGGER.info(fornecedor.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Fornecedor> result = repository.findByNome("tintas");
//		assertEquals(result.size(), 7);
		LOGGER.info("Encontrado: {}", result.size());
	}

	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		
		Fornecedor f1 = new Fornecedor(1, "tintas", "tinta", "rua marataizes n�21","tintas@gmail.com");
		repository.delete(f1);
		
		Fornecedor f2 = new Fornecedor(2, "Testeja", "Piso", "rua colehos n�1565","testeja@gmail.com");
		repository.delete(f2);

		//assertEquals(result.size(), 0);
		LOGGER.info("Exclus�o feita com sucesso");
	}

}



