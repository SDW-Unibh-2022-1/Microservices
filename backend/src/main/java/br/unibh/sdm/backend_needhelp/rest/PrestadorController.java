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

import br.unibh.sdm.backend_needhelp.entidades.PrestadorServicos;
import br.unibh.sdm.backend_needhelp.negocio.PrestadorService;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "prestador")
public class PrestadorController {
	private final PrestadorService prestadorService;

    public PrestadorController(PrestadorService prestadorService){
        this.prestadorService=prestadorService;
    }

    @GetMapping(value = "")
    public List<PrestadorServicos> getPrestadores(){
        return prestadorService.getPrestadores();
    }
    
    @GetMapping(value="{id}")
    public PrestadorServicos getPrestadorById(@PathVariable int id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return (PrestadorServicos) prestadorService.getPrestadorById(id);
        }
        throw new Exception("Prestador com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PrestadorServicos createCliente(@RequestBody @NotNull PrestadorServicos prestador) throws Exception {
         return prestadorService.savePrestador(prestador);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PrestadorServicos updatePrestador(@PathVariable String id, 
    		@RequestBody @NotNull PrestadorServicos prestador) throws Exception {
         return prestadorService.savePrestador(prestador);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updatePrestador(@PathVariable int id) throws Exception {
         prestadorService.deletePrestador(id);
         return true;
    }

}
