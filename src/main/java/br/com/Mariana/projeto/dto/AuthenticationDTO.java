package br.com.Mariana.projeto.dto;

import br.com.Mariana.projeto.entity.UsuarioEntity;

public class AuthenticationDTO {

    private String username;
    private String password;

    public AuthenticationDTO(String username, String password, String encryptedPassword) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public UsuarioEntity getPrincipal() {
		
		return null;
	}
}
