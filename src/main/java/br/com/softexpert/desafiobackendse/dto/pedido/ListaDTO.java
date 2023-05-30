package br.com.softexpert.desafiobackendse.dto.pedido;

import br.com.softexpert.desafiobackendse.dto.pedido.lista.ItemDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ListaDTO {

    @NotEmpty(message = "Não pode estar vazio")
    @Valid
    private List<ItemDTO> itens;

    @NotBlank(message = "Não pode estar vazio")
    private String nome;

    private String urlPagamento;

    private BigDecimal valorSomatoriaItens;

    private BigDecimal valorDesconto;

    private BigDecimal valorGorjeta;

    private BigDecimal valorFinal;

    private BigDecimal valorAposDesconto;

    private BigDecimal valorTaxaEntrega;

}
