package br.com.ifpe.oxefood.modelo.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // busca exata por cpf
    @Query(value = "SELECT c FROM Cliente c WHERE c.cpf= :cpf")
    List<Cliente> consultaPorCpf(String cpf);

    // busca por nome
    @Query(value = "SELECT c FROM Cliente c WHERE c.nome like %:nome% ORDER BY c.nome")
    List<Cliente> consultaPorNome(String nome);
}