package br.com.softexpert.desafiobackendse.dto.pagamento;

import br.com.softexpert.desafiobackendse.dto.PagamentoDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LinkPagamentoDTO extends PagamentoDTO {
}
