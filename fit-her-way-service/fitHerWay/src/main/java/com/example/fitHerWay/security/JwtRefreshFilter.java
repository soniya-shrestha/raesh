//package com.example.fitHerWay.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//public class JwtRefreshFilter extends OncePerRequestFilter {
//
//    private JwtUtils jwtUtil;
//
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        return !request.getServletPath().equals("/api/v1/auth/refresh");
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws IOException {
//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String refreshToken = authHeader.substring(7);
//            String username = jwtUtil.extractUsername(refreshToken);
//
//            if (username != null && jwtUtil.validateToken(refreshToken,
//                    userDetailsService.loadUserByUsername(username))) {
//
//                var userDetails = userDetailsService.loadUserByUsername(username);
//                String newAccessToken = jwtUtil.generateToken(userDetails);
//
//                var tokens = new HashMap<String, String>();
//                tokens.put("accessToken", newAccessToken);
//                tokens.put("refreshToken", refreshToken); // Optional to reissue
//
//                response.setContentType("application/json");
//                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//                return;
//            }
//        }
//
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        new ObjectMapper().writeValue(response.getOutputStream(),
//                new HashMap<>() {{
//                    put("error", "Invalid or expired refresh token");
//                }});
//    }
//}
