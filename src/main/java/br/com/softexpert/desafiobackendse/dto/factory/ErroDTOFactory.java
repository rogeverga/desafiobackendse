package br.com.softexpert.desafiobackendse.dto.factory;

import br.com.softexpert.desafiobackendse.dto.ErroDTO;
import br.com.softexpert.desafiobackendse.dto.erro.factory.CampoDTOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.stream.Collectors;

@Component
public class ErroDTOFactory {

    private static final String ERRO_VALIDACAO_CAMPOS = "Erro de validação nos campos enviados";

    @Autowired
    private CampoDTOFactory campoDTOFactory;

    public ErroDTO criar(String mensagem) {
        ErroDTO erro = new ErroDTO();

        erro.setMensagem(mensagem);

        return erro;
    }

    public ErroDTO criar(WebExchangeBindException webExchangeBindException) {
        ErroDTO erro = new ErroDTO();

        erro.setCampos(
                webExchangeBindException
                        .getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(objectError -> {
                            if (objectError instanceof FieldError) {
                                return
                                    campoDTOFactory
                                            .criar(
                                                ((FieldError) objectError).getField(),
                                                objectError.getDefaultMessage()
                                            );

                            } else {
                                return
                                    campoDTOFactory
                                            .criar(
                                                objectError.getObjectName(),
                                                objectError.getDefaultMessage()
                                            );
                            }
                        })
                        .collect(Collectors.toList())
        );
        erro.setMensagem(ERRO_VALIDACAO_CAMPOS);

        return erro;
    }

}
