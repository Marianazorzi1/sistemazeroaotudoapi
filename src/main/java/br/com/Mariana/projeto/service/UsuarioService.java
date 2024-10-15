package br.com.Mariana.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Mariana.projeto.dto.UsuarioDTO;
import br.com.Mariana.projeto.entity.UsuarioEntity;
import br.com.Mariana.projeto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioDTO> listarTodos(){
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(UsuarioDTO::new).toList();
	}
	
	public void  inserir(UsuarioDTO usuario) {
		UsuarioEntity UsuarioEntity = new UsuarioEntity(usuario);
		usuarioRepository.save(UsuarioEntity);
	}
	
	public UsuarioDTO alterar(UsuarioDTO usuario) {
		UsuarioEntity UsuarioEntity = new UsuarioEntity(usuario);
		return new UsuarioDTO (usuarioRepository.save(UsuarioEntity));
	}
	public void excluir(Long id) {
		UsuarioEntity usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
		
	}
	public UsuarioDTO buscarPorId(Long id) {
		return new UsuarioDTO(usuarioRepository.findById(id).get());
		
	}
}
