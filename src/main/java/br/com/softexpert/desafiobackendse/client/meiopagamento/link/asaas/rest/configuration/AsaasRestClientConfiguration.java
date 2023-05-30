package br.com.softexpert.desafiobackendse.client.meiopagamento.link.asaas.rest.configuration;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Configuration
@Slf4j
public class AsaasRestClientConfiguration {

    @Bean
    @Qualifier("asaasRestClient")
    @SneakyThrows
    public WebClient webClient(
            @Value("${meio-pagamento.asaas.access-token}") String accessToken,
            @Value("${meio-pagamento.asaas.dominio}") String dominio
    ) {
        SslContext sslContext =
                SslContextBuilder
                        .forClient()
                        .trustManager(InsecureTrustManagerFactory.INSTANCE)
                        .build();
        HttpClient httpClient =
                HttpClient
                        .create()
                        .secure(sslProvider -> {
                            sslProvider.sslContext(sslContext);
                        })
                        .resolver(DefaultAddressResolverGroup.INSTANCE);

        return
            WebClient
                .builder()
                .baseUrl(dominio)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("access_token", accessToken);
                    httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
                })
                .build();
    }

}
