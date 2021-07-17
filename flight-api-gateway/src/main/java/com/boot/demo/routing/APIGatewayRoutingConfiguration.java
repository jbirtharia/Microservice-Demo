package com.boot.demo.routing;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * The type Api gateway routing configuration.
 *
 * @author JayendraB  Created on 15/07/21
 */
@Configuration
public class APIGatewayRoutingConfiguration {

    /**
     * Gateway router route locator.
     *
     * @param builder the builder
     * @return the route locator
     */
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(
                        r -> r.path("/posts")
                                .uri("https://jsonplaceholder.typicode.com/posts")
                )
                .route(
                        /**
                         * Only external API starts with http. Internal API like
                         * currency conversion service starts with lb. In this call
                         * incoming URL will be - http://localhost:8765//api/v1/from/{from}/to/{to}
                         * and request will be sent to - http://currency-conversion-service/api/v1/from/{from}/to/{to}
                         *
                         * */
                        r -> r.path("/api/v1/from/{from}/to/{to}")
//                              .filters(f -> f.addRequestHeader("My-Key", "My-Value"))
                                .uri("lb://currency-conversion-service/**")
                )
                .route(
                        /**
                         * New path - /currency/converter/from/{from}/to/{to}
                         * Rewrite path - http://localhost:8765/currency-conversion-service/api/v1/from/{from}/to/{to}
                         * Now we can call currency conversion service API by using new path with
                         * the help of API gateway. The new path will be rewrite by API Gateway.
                         * */
                        r -> r.path("/currency/converter/from/{from}/to/{to}")
                                .filters(f -> f.rewritePath("/currency/converter/(?<remaining>.*)",
                                        "/api/v1/${remaining}"))
                                .uri("lb://currency-conversion-service/")
                )
                .build();
    }
}
