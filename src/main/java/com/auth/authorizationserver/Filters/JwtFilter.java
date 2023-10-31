package com.auth.authorizationserver.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {


// ! extract the token from the header

        String token;

        String  autnHeader=request.getHeader("Authorization");

//        ! check if the token  exists and it has the right token format
//        ? Does the token start with Bearer
//        todo  if the token does not start with bearer return the filter
        if(autnHeader==null||!autnHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

//     todo substring the token to extract the token from the header

token=autnHeader.substring(7);


    }


}
