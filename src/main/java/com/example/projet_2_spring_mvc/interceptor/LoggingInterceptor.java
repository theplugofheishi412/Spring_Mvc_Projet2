package com.example.projet_2_spring_mvc.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.logging.Logger;

/**
 * Intercepteur bonus (HandlerInterceptor) : journalise chaque requête traitée
 * par un @Controller (méthode HTTP, URI, temps de traitement, code retour).
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(LoggingInterceptor.class.getName());
    private static final String ATTR_DEBUT = "requeteDebut";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute(ATTR_DEBUT, System.currentTimeMillis());
        LOGGER.info(() -> "--> " + request.getMethod() + " " + request.getRequestURI());
        return true; // laisser la requête continuer vers le contrôleur
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                 Object handler, Exception ex) {
        long debut = (long) request.getAttribute(ATTR_DEBUT);
        long duree = System.currentTimeMillis() - debut;
        LOGGER.info(() -> "<-- " + request.getMethod() + " " + request.getRequestURI()
                + " [" + response.getStatus() + "] en " + duree + " ms");
    }
}
