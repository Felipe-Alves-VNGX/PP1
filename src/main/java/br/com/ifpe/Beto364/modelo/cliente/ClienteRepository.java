package br.com.ifpe.Beto364.modelo.cliente;

	import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
	import org.springframework.data.jpa.repository.Query;

	public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

}
