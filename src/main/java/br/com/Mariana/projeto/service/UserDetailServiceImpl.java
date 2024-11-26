package br.com.Mariana.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.Mariana.projeto.entity.UsuarioEntity;
import br.com.Mariana.projeto.repository.UsuarioRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  
	    UsuarioEntity usuario = (UsuarioEntity) usuarioRepository.findByLogin(username);

	    
	    if (usuario == null) {
	        throw new UsernameNotFoundException("Usuário não encontrado com o login: " + username);
	    }

	    
	    return UserDetailsImpl.build(usuario);
	}

}