package com.alligator.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class AdminPanelServiceGateway implements WebFluxConfigurer {
	public static final String SERVICE_URI = "lb://admin-panel-service";
	private static final String BASE_PATH = "/api/**";

	@Bean
	public RouteLocator analyticServiceRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(SERVICE_URI,
						route -> route.path(BASE_PATH).uri(SERVICE_URI))
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