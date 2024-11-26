package br.com.Mariana.projeto.entity;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.Mariana.projeto.dto.UsuarioDTO;
import br.com.Mariana.projeto.entity.enums.TipoSituacaoUsuario;
import io.jsonwebtoken.lang.Collections;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "NPL_USUARIO")
@Getter
@AllArgsConstructor




public class UsuarioEntity  implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private String nome;
	
	public Long getId() {
		return id;
	}
	public UsuarioEntity(String login, String senha) {
		this.login = login;
		this.senha = senha;
		
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column (nullable = false, unique = true)
	private String login;
	
	@Column (nullable = false)
	private String senha;
	
	@Column (nullable = false)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column	(nullable = false)
	private TipoSituacaoUsuario situacao = TipoSituacaoUsuario.ATIVO;
	
	public UsuarioEntity(UsuarioDTO usuario) {
		BeanUtils.copyProperties(usuario, this);
	}

	public UsuarioEntity() {
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, id, login, nome, senha);
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioEntity other = (UsuarioEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return Collections.emptyList();
	}

	@Override
	public String getUsername() {
	
		return login;
	}

	@Override
	public String getPassword() {
		
		return senha;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	public void setSituacao(TipoSituacaoUsuario situacao) {
		this.situacao = situacao;
	}
}
