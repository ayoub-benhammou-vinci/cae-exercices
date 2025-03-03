package be.vinci.ipl.cae.cae_exercices_fiche3.services;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos.AuthenticatedUser;
import be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos.Credentials;
import be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos.NewUser;
import be.vinci.ipl.cae.cae_exercices_fiche3.models.entities.User;
import be.vinci.ipl.cae.cae_exercices_fiche3.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final String jwtSecret = "ilovemypizza!";
    private static final long lifetimeJwt = 24*60*60*1000; // 24 hours
    private static final Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) return false;

        createOne(username, password);

        return true;
    }

    public void createOne(String username, String password) {
        User user = new User();
        user.setUsername(username);

        String hashedPassword = this.passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        user.setRole("USER");
        userRepository.save(user);
    }

    public AuthenticatedUser createJwtToken(String username) {
        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + lifetimeJwt))
                .sign(algorithm);

        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setUsername(username);
        authenticatedUser.setToken(token);

        return authenticatedUser;
    }

    public AuthenticatedUser login(String username, String password){
        User user = userRepository.findByUsername(username);
        if(user == null) return null;
        if(!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }
        return createJwtToken(username);
    }

    public String verifyJwtToken(String token){
        try {
            return JWT.require(algorithm).build().verify(token).getClaim("username").asString();
        } catch (Exception e){
            return null;
        }
    }

    public User readOneFromUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Iterable<User> readAllUsers(){
        return userRepository.findAll();
    }

    public void createAnUser(NewUser newUser){
        User user = new User();

        user.setUsername(newUser.getUsername());
        user.setRole(newUser.getRole());
        user.setEmail(newUser.getEmail());

        //Ne pas oublier d'encoder le mot de passe
        String hashedPassword = this.passwordEncoder.encode(newUser.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

}
