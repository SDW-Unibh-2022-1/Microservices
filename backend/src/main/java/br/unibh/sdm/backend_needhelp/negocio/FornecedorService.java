package br.unibh.sdm.backend_needhelp.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_needhelp.entidades.Fornecedor;
import br.unibh.sdm.backend_needhelp.persistencia.FornecedorRepository;

@Service
public class FornecedorService {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final FornecedorRepository fornecedorRepo;

	public FornecedorService(FornecedorRepository fornecedorRepository){
        this.fornecedorRepo=fornecedorRepository;
    }

	public List<Fornecedor> getFornecedores() {
		if (logger.isInfoEnabled()) {
			logger.info("Buscando todos os fornecedores");
		}
		Iterable<Fornecedor> lista = this.fornecedorRepo.findAll();
		if (lista == null) {
			return new ArrayList<Fornecedor>();
		}
		return IteratorUtils.toList(lista.iterator());
	}

	public Fornecedor getFornecedorById(int id) {
		if (logger.isInfoEnabled()) {
			logger.info("Buscando fornecedor com o id {}", id);
		}
		List<Fornecedor> lista = (List<Fornecedor>) this.fornecedorRepo.findById(id);
		if (lista == null || lista.isEmpty()) {
			throw new RuntimeException("Fornecedor com o nome " + id + " nao encontrada");
		}
		return lista.get(0);
	}

	public Fornecedor saveFornecedor(Fornecedor fornecedor) {
		if (logger.isInfoEnabled()) {
			logger.info("Salvando fornecedores com os detalhes {}", fornecedor.toString());
		}
		return this.fornecedorRepo.save(fornecedor);
	}

	public void deleteFornecedor(int id) {
		if (logger.isInfoEnabled()) {
			logger.info("Excluindo fornecedor com id {}", id);
		}
		this.fornecedorRepo.deleteById(id);
	}

	public boolean isFornecedorExists(Fornecedor fornecedor) {
		Optional<Fornecedor> retorno = this.fornecedorRepo.findById(fornecedor.getNome());
		return retorno.isPresent() ? true : false;
	}

	public boolean isFornecedorExists(String id) {
		Optional<Fornecedor> retorno = this.fornecedorRepo.findById(id);
		return retorno.isPresent() ? true : false;
	}

}
