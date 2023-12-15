package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import br.com.ifpe.oxefood.modelo.mensagens.EmailService;
//import br.com.ifpe.oxefood.util.exceptions.//EntidadeNaoEncontradaException;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> filtrar(String nome, String cpf) {

        List<Cliente> listaClientes = repository.findAll();

        if ((nome != null && !"".equals(cpf)) &&
                (cpf == null || "".equals(cpf))) {
            listaClientes = repository.consultaPorNome(nome.trim());

        } else if ((cpf != null && !"".equals(cpf)) &&
                (nome == null || "".equals(nome))) {
            listaClientes = repository.consultaPorCpf(cpf.trim());
        }
        return listaClientes;
    }

    @Transactional
    public Cliente save(Cliente cliente) {

        cliente.setHabilitado(Boolean.TRUE);
        cliente.setVersao(1L);
        cliente.setDataCriacao(LocalDate.now());
        return repository.save(cliente);
    }

    public List<Cliente> findAll() {

        return repository.findAll();
    }

    public Cliente findById(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Cliente clienteAlterado) {

        Cliente cliente = repository.findById(id).get();
        cliente.setNome(clienteAlterado.getNome());
        cliente.setDataNascimento(clienteAlterado.getDataNascimento());
        cliente.setCpf(clienteAlterado.getCpf());
        cliente.setFoneCelular(clienteAlterado.getFoneCelular());
        cliente.setFoneFixo(clienteAlterado.getFoneFixo());

        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);
    }

    @Transactional
    public void delete(Long id) {

        Cliente cliente = repository.findById(id).get();
        cliente.setHabilitado(Boolean.FALSE);
        cliente.setVersao(cliente.getVersao() + 1);

        repository.save(cliente);
    }

}
