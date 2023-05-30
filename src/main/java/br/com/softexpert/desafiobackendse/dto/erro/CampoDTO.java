package br.com.softexpert.desafiobackendse.dto.erro;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CampoDTO {

    private String mensagem;

    private String nome;

}
