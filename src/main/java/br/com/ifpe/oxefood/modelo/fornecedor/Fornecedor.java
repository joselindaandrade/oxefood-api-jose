package br.com.ifpe.oxefood.modelo.fornecedor;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Fornecedor")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor extends EntidadeAuditavel {

   @Column
   private String nome;

   @Column
   private LocalDate dataFuncao;

   @Column
   private String endereco;

   @Column
   private Double valorMercado;

   @Column
   private String paginaWeb;

   @Column
   private String contatoVendedor;

}
