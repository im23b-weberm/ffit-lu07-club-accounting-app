package ch.bzz.controller;

import ch.bzz.generated.model.LoginProject200Response;
import ch.bzz.generated.model.LoginRequest;
import ch.bzz.repository.ProjectRepository;
import ch.bzz.controller.Project;  // ou ch.bzz.controller.Project selon ton projet
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import ch.bzz.util.JwtUtil;
import ch.bzz.generated.api.ProjectApi;



@RestController
public class ProjectApiController implements ProjectApi {

    private final ProjectRepository projectRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    // ✅ Injection correcte du JwtUtil par Spring
    public ProjectApiController(ProjectRepository projectRepository, JwtUtil jwtUtil) {
        this.projectRepository = projectRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<Void> createProject(ch.bzz.generated.model.LoginRequest loginRequest) {
        // Vérifie si le projet existe déjà
        if (projectRepository.existsById(loginRequest.getProjectName())) {
            return ResponseEntity.status(401).build();
        }

        // Crée et sauvegarde le projet
        Project project = new Project();
        project.setProjectName(loginRequest.getProjectName());
        project.setPasswordHash(encoder.encode(loginRequest.getPassword())); // hash du mot de passe

        projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created
    }

    @Override
    public ResponseEntity<ch.bzz.generated.model.LoginProject200Response> loginProject(LoginRequest loginRequest) {
        Project project = projectRepository.findById(loginRequest.getProjectName()).orElse(null);

        // Si le projet n’existe pas
        if (project == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
        }

        // Vérifie le mot de passe avec BCrypt
        if (!encoder.matches(loginRequest.getPassword(), project.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Génère un JWT
        String token = jwtUtil.generateToken(project.getProjectName());

        // Construit la réponse
        LoginProject200Response response = new LoginProject200Response();
        response.setAccessToken(token);
        return ResponseEntity.ok(response); // 200 OK avec le token
    }
}
