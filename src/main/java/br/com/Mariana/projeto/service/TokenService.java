package br.com.Mariana.projeto.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.Mariana.projeto.entity.UsuarioEntity;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(UsuarioEntity usuarioEntity) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("projeto-api")	
					.withSubject(usuarioEntity.getLogin())
					.withExpiresAt(genExpirationDate())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException exeption) {
		
			throw new RuntimeException("Error while generating token", exeption);
		}
		
		
		
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("projeto-api")
					.build()
					.verify(token)
					.getSubject();
			
		} catch (JWTVerificationException exeption) {
			return "";
		}
		
	}
	
	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).atOffset(ZoneOffset.of("-03:00")).toInstant();

	}
}
