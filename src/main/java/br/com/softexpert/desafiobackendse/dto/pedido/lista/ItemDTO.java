package br.com.softexpert.desafiobackendse.dto.pedido.lista;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItemDTO {

    @NotBlank(message = "Não pode estar vazio")
    private String nome;

    @DecimalMin(value = "0.00", message = "Formato inválido")
    @Digits(integer = 8, fraction = 2, message = "Formato inválido")
    @NotNull(message = "Não pode estar vazio")
    private BigDecimal valor;

}
