package com.clientmaster.app.config.jwt;


import com.clientmaster.app.user.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilterWork extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Получаем токен из заголовка
        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Обрезаем префикс и получаем имя пользователя из токена
        String jwt = authHeader.substring(7).trim();
        System.out.println("jwt is "+ jwt);
        var username = jwtService.extractUserName(jwt);
        System.out.println(username);


        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService
                    .userDetailsService()
                    .loadUserByUsername(username);

            // Если токен валиден, то аутентифицируем пользователя
       try{
           if (jwtService.isTokenValid(jwt, userDetails)) {
               SecurityContext context = SecurityContextHolder.createEmptyContext();

               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                       userDetails,
                       null,
                       userDetails.getAuthorities()
               );

               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               context.setAuthentication(authToken);
               SecurityContextHolder.setContext(context);
           }
       } catch (ExpiredJwtException e) {
           System.out.println("catch ExpiredJwtException in filter");
           response.setStatus(HttpStatus.UNAUTHORIZED.value());
           response.getWriter().write("Token is expired");
           response.setContentType(MediaType.APPLICATION_JSON_VALUE);
           return;
       } catch (Exception e) {
           System.out.println("catch Exception in filter ");
           response.setStatus(HttpStatus.UNAUTHORIZED.value());
           response.getWriter().write("Unauthorized");
           response.setContentType(MediaType.APPLICATION_JSON_VALUE);
           return;
       }
        }
        filterChain.doFilter(request, response);
    }
}
