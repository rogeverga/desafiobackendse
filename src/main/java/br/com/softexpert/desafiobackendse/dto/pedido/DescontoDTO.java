package br.com.softexpert.desafiobackendse.dto.pedido;

import br.com.softexpert.desafiobackendse.enumerator.UnidadeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DescontoDTO {

    @NotNull(message = "Não pode estar vazio")
    private UnidadeEnum unidade;

    @DecimalMin(value = "0.00", message = "Formato inválido")
    @Digits(integer = 8, fraction = 2, message = "Formato inválido")
    @NotNull(message = "Não pode estar vazio")
    private BigDecimal valor;

}
