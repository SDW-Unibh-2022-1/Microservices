package br.unibh.sdm.backend_fornecedor.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_fornecedor.entidades.PrestadorServicos;
import br.unibh.sdm.backend_fornecedor.persistencia.PrestadorRepository;


@Service
public class PrestadorService {

	 private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	    
	    private final PrestadorRepository prestadorRepo;

	    public PrestadorService(PrestadorRepository prestadorRepository){
	        this.prestadorRepo=prestadorRepository;
	    }
	    
	    public List<PrestadorServicos> getPrestadores(){
	        if(logger.isInfoEnabled()){
	            logger.info("Buscando todos os prestadores");
	        }
	        Iterable<PrestadorServicos> lista = this.prestadorRepo.findAll();
	        if (lista == null) {
	        	return new ArrayList<PrestadorServicos>();
	        }
	        return IteratorUtils.toList(lista.iterator());
	    }

	    
	    public PrestadorServicos getPrestadorById(int id){
	        if(logger.isInfoEnabled()){
	            logger.info("Buscando Prestadores com o id {}",id);
	        }
	        List<PrestadorServicos> lista = (List<PrestadorServicos>) this.prestadorRepo.findById(id);
	        if(lista == null || lista.isEmpty()){
	            throw new RuntimeException("Cliente com o nome "+id+" nao encontrada");
	        }
	        return lista.get(0);
	    }

	    public PrestadorServicos savePrestador(PrestadorServicos prestador){
	        if(logger.isInfoEnabled()){
	            logger.info("Salvando prestador com os detalhes {}",prestador.toString());
	        }
	        return this.prestadorRepo.save(prestador);
	    }
	    
	    public void deletePrestador(int id){
	        if(logger.isInfoEnabled()){
	            logger.info("Excluindo prestador com id {}",id);
	        }
	        this.prestadorRepo.deleteById(id);
	    }

	    public boolean isPrestadorExists(PrestadorServicos prestador){
	    	Optional<PrestadorServicos> retorno = this.prestadorRepo.findById(prestador.getNome());
	        return retorno.isPresent() ? true:  false;
	    }

	    public boolean isPrestadorExists(String id){
	    	Optional<PrestadorServicos> retorno = this.prestadorRepo.findById(id);
	        return retorno.isPresent() ? true:  false;
	    }

}