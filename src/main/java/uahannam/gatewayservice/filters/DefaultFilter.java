package uahannam.gatewayservice.filters;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class DefaultFilter extends AbstractGatewayFilterFactory<DefaultFilter.Config> {
    public DefaultFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Default Logging Pre Filter
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Default Filter Message : {}", config.getBaseMessage());

            if (config.isPreLogger())
                log.info("Default Filter Start: request id -> {}", request.getId());

            // Default Logging Post Filter
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        if (config.isPostLogger())
                            log.info("Default Filter End: response code -> {}", response.getStatusCode());
                    }))
                    .doOnError(ex -> {
                        log.error("Error During Processing : ", ex);
                    })
                    .then(Mono.empty());
        };
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
