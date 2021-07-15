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
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

        Function<PredicateSpec, Buildable<Route>> routeFunction1 = (p) ->
            p.path("/get")
                    .uri("https://jsonplaceholder.typicode.com/posts");

        /**
         * Only external API starts with http. Internal API like
         * currency conversion service starts with lb. In this call
         * incoming URL will be - http://localhost:8765//api/v1/from/{from}/to/{to}
         * and request will be sent to - http://currency-conversion-service/api/v1/from/{from}/to/{to}
         *
         * */
        Function<PredicateSpec, Buildable<Route>> routeFunction2 = (p) ->
                p.path("/api/v1/from/{from}/to/{to}")
                        .uri("lb://currency-conversion-service/api/v1/from/{from}/to/{to}");

        return builder.routes()
                .route(routeFunction1)
                .build();
    }
}
