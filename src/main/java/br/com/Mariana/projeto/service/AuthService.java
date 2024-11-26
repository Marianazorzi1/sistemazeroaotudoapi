package br.com.Mariana.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.Mariana.projeto.dto.AcessDTO;
import br.com.Mariana.projeto.dto.AuthenticationDTO;
import br.com.Mariana.projeto.repository.UsuarioRepository;
import br.com.Mariana.projeto.security.jwt.JwtUtils;

@Service
public class AuthService implements UserDetailsService{

	@Autowired
	private AuthenticationManager authenticatioManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	UsuarioRepository repository;
	
	public AcessDTO login(AuthenticationDTO authDto) {
		
		try {

		UsernamePasswordAuthenticationToken userAuth = 
				new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());

		Authentication authentication = authenticatioManager.authenticate(userAuth);

		UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();
		
		String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
		
		AcessDTO accessDto = new AcessDTO(token);
		
		return accessDto;
		
		}catch(BadCredentialsException e) {
			
		}
		return new AcessDTO("Acesso negado");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repository.findByLogin(username);
	}
}