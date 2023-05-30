package br.com.softexpert.desafiobackendse;

import br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.AsaasRestClient;
import br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.configuration.AsaasRestClientConfiguration;
import br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.request.factory.PaymentLinkRequestFactory;
import br.com.softexpert.desafiobackendse.client.meiopagamento.link.provider.MeioPagamentoLinkClientProvider;
import br.com.softexpert.desafiobackendse.controller.LinkPagamentoController;
import br.com.softexpert.desafiobackendse.dto.erro.factory.CampoDTOFactory;
import br.com.softexpert.desafiobackendse.dto.factory.ErroDTOFactory;
import br.com.softexpert.desafiobackendse.dto.pagamento.LinkPagamentoDTO;
import br.com.softexpert.desafiobackendse.service.LinkPagamentoService;
import br.com.softexpert.desafiobackendse.service.PedidoService;
import br.com.softexpert.desafiobackendse.util.CalculoUtil;
import br.com.softexpert.desafiobackendse.util.StringUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.InputStream;

@Import(
		value = {
				AsaasRestClient.class,
				AsaasRestClientConfiguration.class,
				CalculoUtil.class,
				CampoDTOFactory.class,
				ErroDTOFactory.class,
				LinkPagamentoService.class,
				MeioPagamentoLinkClientProvider.class,
				PaymentLinkRequestFactory.class,
				PedidoService.class,
				StringUtil.class
		}
)
@WebFluxTest(controllers = LinkPagamentoController.class)
public class LinkPagamentoTests {

	@Autowired
	private WebTestClient webTestClient;

	@SneakyThrows
	@Test
	public void criacaoLink() {
		TypeReference<LinkPagamentoDTO> typeReference = new TypeReference<LinkPagamentoDTO>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/massas/criacao-link.json");

		LinkPagamentoDTO linkPagamento = new ObjectMapper().readValue(inputStream, typeReference);

		webTestClient
				.post()
				.uri("/pagamentos/link")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(linkPagamento)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.pedido.listas[0].urlPagamento").isEmpty();
	}

	@SneakyThrows
	@Test
	public void validacaoCamposVazios() {
		TypeReference<LinkPagamentoDTO> typeReference = new TypeReference<LinkPagamentoDTO>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/massas/validacao-campos-vazios.json");

		LinkPagamentoDTO linkPagamento = new ObjectMapper().readValue(inputStream, typeReference);

		webTestClient
				.post()
				.uri("/pagamentos/link")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(linkPagamento)
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody()
				.jsonPath("$.campos[?(@.nome anyof ['pedido.gorjeta.valor'])]");
	}

}