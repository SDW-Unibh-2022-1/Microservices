package br.unibh.sdm.backend_needhelp.rest;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.unibh.sdm.backend_needhelp.entidades.Fornecedor;
import br.unibh.sdm.backend_needhelp.negocio.FornecedorService;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "fornecedor")
public class FornecedorController {
	
	private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService){
        this.fornecedorService=fornecedorService;
    }

    @GetMapping(value = "")
    public List<Fornecedor> getFornecedores(){
        return fornecedorService.getFornecedores();
    }
    
    @GetMapping(value="{id}")
    public Fornecedor getClienteById(@PathVariable int id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return (Fornecedor) fornecedorService.getFornecedorById(id);
        }
        throw new Exception("Fornecedores com id "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Fornecedor createFornecedor(@RequestBody @NotNull Fornecedor fornecedor) throws Exception {
         return fornecedorService.saveFornecedor(fornecedor);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Fornecedor updateFornecedor(@PathVariable String id, 
    		@RequestBody @NotNull Fornecedor fornecedor) throws Exception {
         return fornecedorService.saveFornecedor(fornecedor);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updateFornecedor(@PathVariable int id) throws Exception {
         fornecedorService.deleteFornecedor(id);
         return true;
    }

}
