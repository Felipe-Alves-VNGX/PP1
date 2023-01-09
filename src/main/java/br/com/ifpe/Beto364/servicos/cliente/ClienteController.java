package br.com.ifpe.Beto364.servicos.cliente;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.Beto364.modelo.cliente.Cliente;
import br.com.ifpe.Beto364.modelo.cliente.ClienteService;
import br.com.ifpe.Beto364.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController extends GenericController {

    @Autowired
    private ClienteService clienteService;
    @ApiOperation(value = "Serviço responsável por salvar um cliente no sistema.")
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest request) {

	validarPreenchimentoChave(request.getCpf());
	Cliente cliente = clienteService.save(request.buildCliente());
	return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Serviço responsável por obter um cliente referente ao Id passado na URL.")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o cliente."),
	    @ApiResponse(code = 401, message = "Acesso não autorizado."),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
	    @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
	    @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
    })

    @GetMapping("/{id}")
    public Cliente obterClientePorID(@PathVariable Long id) {

	return clienteService.obterClientePorID(id);
    }
    
    
    @PutMapping("/{id}")
    @ApiOperation(value = "Serviço responsável por atualizar as informações da categoria de produto no sistema.")
    public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody ClienteRequest request) {

	clienteService.update(id, request.buildCliente());
	return ResponseEntity.ok().build();
    }


}

