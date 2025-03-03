package be.vinci.ipl.cae.cae_exercices_fiche3.configuration;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.entities.User;
import be.vinci.ipl.cae.cae_exercices_fiche3.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JWTAuthentificationFiltrer extends OncePerRequestFilter {

  private final UserService userService;

  public JWTAuthentificationFiltrer(UserService userService) {
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = request.getHeader("Authorization");
    if (token != null) {
      String username = userService.verifyJwtToken(token);
      if (username != null) {
        User user = userService.readOneFromUsername(username);
        if (user != null) {
          List<GrantedAuthority> authorities = new ArrayList<>();
          if (user.getRole().equals("ADMIN")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add((new SimpleGrantedAuthority("ROLE_USER")));
          }
          if (user.getRole().equals("USER")) authorities.add((new SimpleGrantedAuthority("ROLE_USER")));
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    }
    filterChain.doFilter(request, response);
  }
}
