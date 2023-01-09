package br.com.ifpe.Beto364.modelo.cliente;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.Beto364.modelo.acesso.UsuarioService;
import br.com.ifpe.Beto364.util.entity.GenericService;
import br.com.ifpe.Beto364.util.exception.EntityAlreadyExistsException;

@Service
public class ClienteService extends GenericService {
    
    @Autowired
    private ClienteRepository repository;
    
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Cliente save(Cliente cliente) {
    	
    usuarioService.save(cliente.getUsuario());

	super.validarRegistroVazio(cliente.getCpf(), "Cpf");
	this.validarClienteExistente(cliente, null);
	super.preencherCamposAuditoria(cliente);

	return repository.save(cliente);
    }
    
    @Transactional
    public Cliente obterClientePorID(Long id) {

	return repository.findById(id).get();
    }
    
    @Transactional
    public void update(Long id, Cliente clienteAlterado) {

	 validarClienteExistente(clienteAlterado, id);
	
	 Cliente cliente = this.obterClientePorID(id);
	 cliente.updateFrom(clienteAlterado);
	 super.preencherCamposAuditoria(cliente);

	 repository.save(cliente);
    }
    
    @Transactional
    public void delete(Long id) {

	Cliente cliente = this.obterClientePorID(id);
	cliente.setHabilitado(Boolean.FALSE);
	super.preencherCamposAuditoria(cliente);

	repository.save(cliente);
    }


    private void validarClienteExistente(Cliente clienteParam, Long id) {

	if (StringUtils.isNotBlank(clienteParam.getCpf())) {

	    Cliente cliente = this.obterClientePorID(clienteParam.getId());

	    if (id == null) { // O id será null quando este método for chamado para validar a inclusão de novos clientes

		if (cliente != null) {
		    throw new EntityAlreadyExistsException(Cliente.LABEL, "Cpf");
		}

	    } else { // O id NÃO será null quando este método for chamado para validar a alteração de clientes

		if (cliente != null && cliente.getId() != id) {
		    throw new EntityAlreadyExistsException(Cliente.LABEL, "CPf");
		}
	    }
	}
    }
}

