package com.alligator.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class LendingServiceGateway implements WebFluxConfigurer {
	public static final String SERVICE_URI = "lb://lending-service";
	private static final String BASE_PATH = "/api/**";
	private static final String SWAGGER_UI_PATH = "/swagger-ui/**";
	private static final String SWAGGER_DOCS_PATH = "/docs/**";

	@Bean
	public RouteLocator lendingServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(SERVICE_URI,
						route -> route.path(BASE_PATH, SWAGGER_UI_PATH, SWAGGER_DOCS_PATH).uri(SERVICE_URI))
				.build();
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name())
				.allowedHeaders("*");
	}
}