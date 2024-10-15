package br.com.Mariana.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Mariana.projeto.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

}
