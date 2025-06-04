package lk.ijse.cmjd109.LostAndFoundApp.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.cmjd109.LostAndFoundApp.security.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@Component
@RequiredArgsConstructor
@Order(1)
public class AuthFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;
    private final UserDetailServiceImpl userDetailServiceImpl;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var jwtToken=getJwtToken(request);
            if(jwtToken!=null&&jwtUtils.validateToken(jwtToken)) {
               var userName=jwtUtils.getUserNameFromToken(jwtToken);
               var userDetails=userDetailServiceImpl.loadUserByUsername(userName);
               var authToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               authToken.setDetails(new WebAuthenticationDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        try {
//            var jwtToken = getJwtToken(request);
//            if (jwtToken != null && jwtUtils.validateToken(jwtToken)) {
//                var userName = jwtUtils.getUserNameFromToken(jwtToken);
//                var roles = jwtUtils.getRolesFromToken(jwtToken); // ⬅️ NEW
//                List<SimpleGrantedAuthority> authorities = Arrays.stream(roles.split(","))
//                        .map(String::trim)
//                        .map(SimpleGrantedAuthority::new)
//                        .toList();
//
//                var authToken = new UsernamePasswordAuthenticationToken(userName, null, authorities);
//                authToken.setDetails(new WebAuthenticationDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        filterChain.doFilter(request, response);
//    }

    //    private String getJwtTOken(HttpServletRequest request) {
//        String authHeader = request.getHeader("Authorization");
//        if(StringUtils.hasText(authHeader)&&authHeader.startsWith("Bearer")){
//            return authHeader.substring(7);
//        }else{
//            return null;
//        }
//    }
    private String getJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Remove "Bearer " (with space)
        }
        return null;
    }

}
