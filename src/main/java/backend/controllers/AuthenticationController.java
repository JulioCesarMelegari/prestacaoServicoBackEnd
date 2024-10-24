package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dtos.AuthenticationDto;
import backend.dtos.RegisterDto;
import backend.entities.UserRole;
import backend.entities.Usuario;
import backend.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationDto> login(@RequestBody @Valid AuthenticationDto data){
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisterDto> register(@RequestBody @Valid RegisterDto data){
		if(this.repository.findByUsername(data.username()) != null)
			return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		Usuario newUsuario = new Usuario(data.username(), encryptedPassword, UserRole.USER);
		this.repository.save(newUsuario);
		return ResponseEntity.ok().build();
	}

}
