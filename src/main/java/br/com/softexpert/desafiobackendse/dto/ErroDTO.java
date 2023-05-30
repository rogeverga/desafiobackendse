package br.com.softexpert.desafiobackendse.dto;

import br.com.softexpert.desafiobackendse.dto.erro.CampoDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErroDTO {

    private List<CampoDTO> campos;

    private String mensagem;

}
