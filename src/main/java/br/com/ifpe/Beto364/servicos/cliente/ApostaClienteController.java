package br.com.ifpe.Beto364.servicos.cliente;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.Beto364.modelo.cliente.ApostaCliente;
import br.com.ifpe.Beto364.modelo.cliente.ApostaClienteService;
import br.com.ifpe.Beto364.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;

/**
 * @author Roberto Alencar
 *
 */
@RestController
@RequestMapping("/api/apostacliente")
public class ApostaClienteController extends GenericController {

    @Autowired
    private ApostaClienteService apostaService;   
    
    @ApiOperation(value = "Rota responsável por adicionar uma aposta a um cliente já existente.")
    @PostMapping("/{clienteId}/aposta")
    public ResponseEntity<ApostaCliente> adicionarApostaCliente(@RequestBody @Valid ApostaClienteRequest request, @PathVariable("clienteId") Long clienteId) {

	ApostaCliente aposta = apostaService.adicionarApostaCliente(clienteId, request.buildAposta());
	return new ResponseEntity<ApostaCliente>(aposta, HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Rota responsável por atualizar a aposta de um determinado cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ApostaCliente> atualizarApostaCliente(@PathVariable("id") Long id, @RequestBody ApostaClienteRequest request) {

	ApostaCliente aposta = apostaService.atualizarApostaCliente(id, request.buildAposta());
	return new ResponseEntity<ApostaCliente>(aposta, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Rota responsável por remover uma aposta de um determinado cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerApostaCliente(@PathVariable Long id) {

	apostaService.removerApostaCliente(id);
	return ResponseEntity.noContent().build();
    }
}
