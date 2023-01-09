package br.com.ifpe.Beto364.modelo.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.Beto364.util.entity.GenericService;
import br.com.ifpe.Beto364.util.exception.EntidadeNaoEncontradaException;
import br.com.ifpe.Beto364.util.exception.ResultadoDefinidoException;
import br.com.ifpe.Beto364.util.exception.SaldoInsuficienteException;

@Service
public class ApostaClienteService extends GenericService {
    
    @Autowired
    private ApostaClienteRepository repository;

    @Autowired
    private ClienteService clienteService;
    
    @Transactional
    public ApostaCliente findById(Long id) {

    	Optional<ApostaCliente> consulta = repository.findById(id);
    	
    	if (consulta.isPresent()) {
    	    return consulta.get();
    	} else {
    	    throw new EntidadeNaoEncontradaException("Cliente", id);
    	}

    }

    @Transactional
    public ApostaCliente adicionarApostaCliente(Long clienteId, ApostaCliente aposta) {

	Cliente cliente = clienteService.obterClientePorID(clienteId);
	
	if (aposta.getValor() > cliente.getSaldo()) {
		throw new SaldoInsuficienteException();
	}
	//Primeiro salva o ApostaCliente
	cliente.setSaldo(cliente.getSaldo()-aposta.getValor());
	aposta.setCliente(cliente);
	aposta.setHabilitado(Boolean.TRUE);
	repository.save(aposta);
	
	//Depois acrescenta o endereço criado ao cliente e atualiza o cliente
	List<ApostaCliente> listaApostaCliente = cliente.getApostas();
	
	if (listaApostaCliente == null) {
	    listaApostaCliente = new ArrayList<ApostaCliente>();
	}
	
	listaApostaCliente.add(aposta);
	cliente.setApostas(listaApostaCliente);
	clienteService.save(cliente);
	
	return aposta;
    }
    @Transactional
    public ApostaCliente atualizarApostaCliente(Long id, ApostaCliente apostaAlterada) {

	ApostaCliente aposta = this.findById(id);
	if (aposta.isResultado()) {
		throw new ResultadoDefinidoException();
	}
		aposta.updateFrom(apostaAlterada);
		return repository.save(aposta);
    }
    
    @Transactional
    public void removerApostaCliente(Long id) {
    ApostaCliente aposta = this.findById(id);	
    if (aposta.isResultado()) {
		throw new ResultadoDefinidoException();
	} 
    
	aposta.setHabilitado(Boolean.FALSE);
	repository.save(aposta);

	Cliente cliente = clienteService.obterClientePorID(aposta.getId());
	cliente.setSaldo(cliente.getSaldo()+aposta.getValor());
	cliente.getApostas().remove(aposta);
	clienteService.save(cliente);
    }
    }