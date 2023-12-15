package br.com.ifpe.oxefood.modelo.fornecedor;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository repository;

    @Transactional
    public Fornecedor save(Fornecedor fornecedor) {

        fornecedor.setHabilitado(Boolean.TRUE);
        fornecedor.setVersao(1L);
        fornecedor.setDataCriacao(LocalDate.now());
        return repository.save(fornecedor);
    }

    public List<Fornecedor> findAll() {

        return repository.findAll();
    }

    public Fornecedor findById(Long id) {

        return repository.findById(id).get();
    }

    /* Transactional para alteração do banco */
    @Transactional
    /*
     * id Long EntidadeNegocio
     * ...
     * versao Long EntidadeAuditavel
     * ...
     * nome String Fornecedor
     * endereco String Fornecedor
     * dataFundacao LocalDate Fornecedor
     * valorMercado Double Fornecedor
     * paginaWeb String Fornecedor
     * contatoVendedor String Fornecedor
     */
    public void update(Long id, Fornecedor fornecedorAlterado) {

        Fornecedor fornecedor = repository.findById(id).get();
        fornecedor.setNome(fornecedorAlterado.getNome());
        fornecedor.setEndereco(fornecedorAlterado.getEndereco());
        fornecedor.setDataFuncao(fornecedorAlterado.getDataFuncao());
        fornecedor.setValorMercado(fornecedorAlterado.getValorMercado());
        fornecedor.setPaginaWeb(fornecedorAlterado.getPaginaWeb());
        fornecedor.setContatoVendedor(fornecedorAlterado.getContatoVendedor());

        /* altera a verção começa de um e vai em diante */
        fornecedor.setVersao(fornecedor.getVersao() + 1);
        repository.save(fornecedor);
    }

    @Transactional
    public void delete(Long id) {

        Fornecedor fornecedor = repository.findById(id).get();
        fornecedor.setHabilitado(Boolean.FALSE);
        fornecedor.setVersao(fornecedor.getVersao() + 1);

        repository.save(fornecedor);
    }
}
