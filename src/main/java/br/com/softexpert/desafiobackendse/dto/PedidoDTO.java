package br.com.softexpert.desafiobackendse.dto;

import br.com.softexpert.desafiobackendse.dto.pedido.*;
import br.com.softexpert.desafiobackendse.enumerator.TipoPedidoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PedidoDTO {

    @Valid
    private DescontoDTO desconto;

    @Valid
    private GorjetaDTO gorjeta;

    @NotEmpty(message = "Não pode estar vazio")
    @Valid
    private List<ListaDTO> listas;

    @DecimalMin(value = "0.00", message = "Formato inválido")
    @Digits(integer = 8, fraction = 2, message = "Formato inválido")
    private BigDecimal valorTaxaEntrega;

    @NotNull(message = "Não pode estar vazio")
    private TipoPedidoEnum tipo;

    private BigDecimal valorAposDesconto;

    private BigDecimal valorDesconto;

    private BigDecimal valorFinal;

    private BigDecimal valorGorjeta;

    private BigDecimal valorSomatoriaItensListas;

}