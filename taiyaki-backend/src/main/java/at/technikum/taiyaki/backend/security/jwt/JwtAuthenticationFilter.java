package at.technikum.taiyaki.backend.security.jwt;

import at.technikum.taiyaki.backend.security.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String BEARER_TYPE = "Bearer ";

    private final JwtDecoder jwtDecoder;
    private final JwtToPrincipalConverter jwtToPrincipalConverter;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        extractTokenFromRequest(request)
                .map(jwtDecoder::decode)
                .map(jwtToPrincipalConverter::converter)
                .map(UserPrincipalAuthenticationToken::new)
                .ifPresent(authentication -> {
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // Authentication logging
                    if (logger.isDebugEnabled() && authentication.getPrincipal() instanceof UserPrincipal p) {
                        logger.debug("Authenticated {} {} as userId={}, username={}",
                                request.getMethod(),
                                request.getRequestURI(),
                                p.getId(),
                                p.getUsername());
                    }
                });

        filterChain.doFilter(request, response);
    }

    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(header) && header.startsWith(BEARER_TYPE)) {
            return Optional.of(header.substring(BEARER_TYPE.length()));
        }
        return Optional.empty();
    }
}
