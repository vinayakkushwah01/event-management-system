package com.vinayak.event_management.config;

import java.io.IOException;

//import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vinayak.event_management.securityService.JWTService;
import com.vinayak.event_management.securityService.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFillter extends OncePerRequestFilter {
       
    
    @Autowired
    private  JWTService jwtService;

    @Autowired
    ApplicationContext context ;
    
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
           // Bearer ndsnjnsd.sdnsn.jsnnwenknf    --> this is the Receved Format at Server Side 
           String authHeader = request.getHeader("Authorization");
           String token = null;
           String username = null;
           if(authHeader != null && authHeader.startsWith("Bearer ")){
            token= authHeader.substring(7);
            username = jwtService.extractUserName(token);
           }

           if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            
                UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            
                if(jwtService.validateToken(token,userDetails  )){


                    String role = jwtService.extractRole(token);

                    if (userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_" + role))) {

                        // System.out.println("JWT Filltyer " + userDetails.getAuthorities().stream().);

                                UsernamePasswordAuthenticationToken authToken = 
                                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                
                                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                
                                SecurityContextHolder.getContext().setAuthentication(authToken);
                    }            
                
                }
           }

           try {
            filterChain.doFilter(request, response);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   
    

}
