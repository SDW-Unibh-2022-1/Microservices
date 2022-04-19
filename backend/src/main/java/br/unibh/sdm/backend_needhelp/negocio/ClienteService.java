package br.unibh.sdm.backend_needhelp.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_needhelp.entidades.Cliente;
import br.unibh.sdm.backend_needhelp.persistencia.ClienteRepository;


@Service
public class ClienteService {
	
	 private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	    
	    private final ClienteRepository clienteRepo;

	    public ClienteService(ClienteRepository clienteRepository){
	        this.clienteRepo=clienteRepository;
	    }
	    
	    public List<Cliente> getClientes(){
	        if(logger.isInfoEnabled()){
	            logger.info("Buscando todos os objetos");
	        }
	        Iterable<Cliente> lista = this.clienteRepo.findAll();
	        if (lista == null) {
	        	return new ArrayList<Cliente>();
	        }
	        return IteratorUtils.toList(lista.iterator());
	    }    

	    public Cliente getClienteByNome(String nome){
	        if(logger.isInfoEnabled()){
	            logger.info("Buscando Clientes com o nome {}",nome);
	        }
	        Optional<Cliente> retorno = this.clienteRepo.findByNome(nome);
	        if(!retorno.isPresent()){
	            throw new RuntimeException("Cliente com o nome "+ nome+" nao encontrada");
	        }
	        return retorno.get();
	    }
	    
	    public List<Cliente> getClienteById(int id){
	    	if(logger.isInfoEnabled()){
	            logger.info("Buscando todos os objetos");
	        }
	        Iterable<Cliente> lista = this.clienteRepo.findById(id);
	        if (lista == null) {
	        	return new ArrayList<Cliente>();
	        }
	        return IteratorUtils.toList(lista.iterator());
	    }
	    
	    public Cliente saveCliente(Cliente cliente){
	        if(logger.isInfoEnabled()){
	            logger.info("Salvando Clientes com os detalhes {}",cliente.toString());
	        }
	        return this.clienteRepo.save(cliente);
	    }
	    
	    public void deleteCliente(String id){
	        if(logger.isInfoEnabled()){
	            logger.info("Excluindo Cliente com id {}",id);
	        }
	        this.clienteRepo.deleteById(id);
	    }

	    public boolean isClienteExists(Cliente cliente){
	    	Optional<Cliente> retorno = this.clienteRepo.findById(cliente.getNome());
	        return retorno.isPresent() ? true:  false;
	    }

}
