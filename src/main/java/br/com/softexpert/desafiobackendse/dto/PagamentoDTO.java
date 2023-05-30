package br.com.softexpert.desafiobackendse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class PagamentoDTO {

    @Valid
    private PedidoDTO pedido;

}
