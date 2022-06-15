package br.unibh.sdm.backend_fornecedor.tests;

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

import br.unibh.sdm.backend_fornecedor.entidades.PrestadorServicos;
import br.unibh.sdm.backend_fornecedor.entidades.Servicos;
import br.unibh.sdm.backend_fornecedor.persistencia.PrestadorRepository;





@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, PrestadorTests.DynamoDBConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrestadorTests {
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(PrestadorTests.class);

	@Configuration
	@EnableDynamoDBRepositories(basePackageClasses = { PrestadorRepository.class })
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
	private PrestadorRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		
		PrestadorServicos S1 = new PrestadorServicos(1, "valter", Servicos.BOMBEIRO_HIDRAULICO.getServico(),"valter@gmail.com ");
		PrestadorServicos S2 = new PrestadorServicos(2, "Izabella", Servicos.DIARISTA.getServico(),"iza@gmail.com ");

		repository.save(S1);
		repository.save(S2);

		Iterable<PrestadorServicos> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (PrestadorServicos prestador : lista) {
			LOGGER.info(prestador.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<PrestadorServicos> result = repository.findByNome("Valter");
//		assertEquals(result.size(), 7);
		LOGGER.info("Encontrado: {}", result.size());
	}

	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		
		PrestadorServicos S1 = new PrestadorServicos(1, "valter", Servicos.BOMBEIRO_HIDRAULICO.getServico(),"valter@gmail.com ");
		repository.delete(S1);
		PrestadorServicos S2 = new PrestadorServicos(2, "Izabella", Servicos.DIARISTA.getServico(),"iza@gmail.com ");
		repository.delete(S2);

		//assertEquals(result.size(), 0);
		LOGGER.info("Exclusão feita com sucesso");
	}
	
}