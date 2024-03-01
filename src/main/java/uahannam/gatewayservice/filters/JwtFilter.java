package uahannam.gatewayservice.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uahannam.gatewayservice.util.JwtUtil;

import java.nio.charset.StandardCharsets;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (!request.getHeaders().containsKey("Authorization"))
                return tokenIsNotAvailable(response, "token not contained");

            String token = request.getHeaders().get("Authorization").get(0);

            if (!jwtUtil.verityToken(token)) {
                return tokenIsNotAvailable(response, "token is not available");
            }

            setCustomHeader(request, token);

            // Default Logging Post Filter
            return chain.filter(exchange);
        };
    }

    private void setCustomHeader(ServerHttpRequest request, String token) {
        request.mutate().header("X-CUSTOM-USER",
                jwtUtil.extractUserId(token));
    }

    private Mono<Void> tokenIsNotAvailable(ServerHttpResponse response, String message) {
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.writeWith(Mono.just(wrap));
    }

}
