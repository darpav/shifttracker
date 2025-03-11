package com.hita.shifttracker.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")  // Apply the filter to all URLs
public class CSPFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Set the Content Security Policy header to allow iframe embedding from specific domains
        httpResponse.setContentType("text/html");
        httpResponse.setHeader("Content-Security-Policy",
                "default-src 'self'; frame-ancestors 'self' https://apache-superset-railway-19vo-production.up.railway.app; script-src 'self';");

        // Optionally set CORS headers (if needed)
        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8080"); // Allow requests from your Spring Boot app
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Continue with the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
    }
}

