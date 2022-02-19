package com.mec.mundoDisney.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.mec.mundoDisney.security.model.UsuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;


@Component
public class JwtProvider {
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	private Key getSigningKey() {
		  byte[] keyBytes = Decoders.BASE64.decode(this.secret);
		  return Keys.hmacShaKeyFor(keyBytes);
		}
	
	
	//genera el token
	public String generateToken(Authentication authentication) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
		System.out.println(this.getSigningKey().toString());
		return Jwts.builder()
				.setSubject(usuarioPrincipal.getUsername()) //nombre del usuario
				.setIssuedAt(new Date())  //fecha de creacion
				.setExpiration(new Date(new Date().getTime() + expiration*1000))	//fecha de expiracion
				.signWith(this.getSigningKey())		//firma
				.compact();		//compilar
		
	}
	
	//devuelve el usuario
	public String getUserNameFromToken(String token) {
		System.out.println(this.getSigningKey().toString() + " metodo get User from token");
		return Jwts.parserBuilder().setSigningKey(this.getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
	}
	
	//valida el token
	public boolean validateToken(String token) {
		try {
			System.out.println(this.getSigningKey().toString() + "metodo validate token");
			Jwts.parserBuilder().setSigningKey(this.getSigningKey()).build().parseClaimsJws(token);
			return true;
		} 
		//posibles excepciones al validar el token
		 catch (MalformedJwtException e) {
			logger.error("token mal formado");
		} catch (UnsupportedJwtException e) {
			logger.error("token no soportado");
		} catch (ExpiredJwtException e) {
			logger.error("token expirado");
		} catch (IllegalArgumentException e) {
			logger.error("token vacio");
		} catch (SignatureException e) {
			logger.error("fail en la firma");
		}
		return false;
	}
}
