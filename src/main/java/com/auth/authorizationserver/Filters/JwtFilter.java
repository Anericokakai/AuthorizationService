package com.auth.authorizationserver.Filters;

import com.auth.authorizationserver.Service.JWtService;
import com.auth.authorizationserver.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private  final JWtService jWtService;
    private  final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {


// ! extract the token from the header

        String token;
        String username;

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
        username=jWtService.extractEmail(token);
        if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);

            if(jWtService.isTokenValid(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }

filterChain.doFilter(request,response);


    }


}
