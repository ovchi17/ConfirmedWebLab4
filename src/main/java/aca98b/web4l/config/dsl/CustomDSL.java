package aca98b.web4l.config.dsl;

import aca98b.web4l.session.SessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Component
public class CustomDSL extends AbstractHttpConfigurer<CustomDSL, HttpSecurity> {

    private final SessionFilter sessionFilter;

    @Autowired
    public CustomDSL(final SessionFilter sessionFilter) {
        this.sessionFilter = sessionFilter;
    }

    @Override
    public void configure(final HttpSecurity http) {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        CorsFilter filter = new CorsFilter(source);

        http.addFilter(filter);
        http.addFilterBefore(sessionFilter, UsernamePasswordAuthenticationFilter.class);
    }
}