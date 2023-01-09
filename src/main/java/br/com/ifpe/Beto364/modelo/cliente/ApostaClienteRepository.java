package br.com.ifpe.Beto364.modelo.cliente;

	import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

	public interface ApostaClienteRepository extends JpaRepository<ApostaCliente, Long>, JpaSpecificationExecutor<ApostaCliente> {


}
