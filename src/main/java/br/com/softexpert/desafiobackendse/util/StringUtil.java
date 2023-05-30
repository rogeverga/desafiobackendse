package br.com.softexpert.desafiobackendse.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

    public String substituir(String texto, String textoASubstituir, String novoTexto) {
        return texto.replace(textoASubstituir, novoTexto);
    }

}
