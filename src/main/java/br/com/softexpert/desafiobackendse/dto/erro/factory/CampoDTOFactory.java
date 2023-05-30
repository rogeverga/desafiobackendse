package br.com.softexpert.desafiobackendse.dto.erro.factory;

import br.com.softexpert.desafiobackendse.dto.erro.CampoDTO;
import org.springframework.stereotype.Component;

@Component
public class CampoDTOFactory {

    public CampoDTO criar(String nome, String mensagem) {
        CampoDTO campoDTO = new CampoDTO();

        campoDTO.setMensagem(mensagem);
        campoDTO.setNome(nome);

        return campoDTO;
    }

}
