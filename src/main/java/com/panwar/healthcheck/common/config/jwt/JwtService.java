package com.panwar.healthcheck.common.config.jwt;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private int jwtExpirationMs;

	/**
	 * Extracts the username from a JWT token.
	 * 
	 * This method takes a JWT token as input and returns the username extracted
	 * from the token. The username is typically stored in the "sub" claim of the
	 * token.
	 * 
	 * @param token the JWT token
	 * @return the extracted username, or null if the token is invalid or cannot be
	 *         parsed
	 * @see #extractClaim(String, Function)
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Validates a JWT token.
	 * 
	 * This method takes a JWT token and a username as input, and returns true if
	 * the token is valid and belongs to the given username. A token is considered
	 * valid if it has not expired and its signature is correct.
	 * 
	 * @param token    the JWT token
	 * @param username the username to validate against
	 * @return true if the token is valid and belongs to the given username, false
	 *         otherwise
	 * @see #extractUsername(String)
	 * @see #isTokenExpired(String)
	 */
	public boolean isValidToken(String token, String username) {
		String extractedUsername = extractUsername(token);
		return extractedUsername.equals(username) && !isTokenExpired(token);
	}

	/**
	 * Generates a JWT token for a given username.
	 * 
	 * This method takes a username and a set of extra claims as input, and returns
	 * a JWT token that can be used to authenticate the user. The token is signed
	 * with a secret key and has a limited lifetime, which is configured by the
	 * tokenExpiryInSeconds property.
	 * 
	 * @param extraClaims a map of extra claims to include in the token
	 * @param username    the username to generate the token for
	 * @return a JWT token that can be used to authenticate the user
	 * @throws JwtException if there is an error generating the token
	 * @see #jwtExpirationMs
	 */
	public String generateToken(Map<String, Object> extraClaims, String username) throws JwtException {
		final Date now = new Date();
		Date expiryTime = new Date(now.getTime() + jwtExpirationMs * 1000);

		return Jwts.builder().claims(extraClaims).subject(username).issuedAt(now).expiration(expiryTime)
				.signWith(getSigningKey(), Jwts.SIG.HS256).compact();
	}

	/**
	 * Checks if a JWT token has expired.
	 * 
	 * This method takes a JWT token as input and returns true if the token has
	 * expired, false otherwise.
	 * 
	 * @param token the JWT token
	 * @return true if the token has expired, false otherwise
	 */
	private boolean isTokenExpired(String token) {
		final var expiration = extractClaim(token, Claims::getExpiration);
		final var now = System.currentTimeMillis();

		if (expiration != null) {
			return now < expiration.getTime();
		}

		return false;
	}

	/**
	 * Extracts a claim from the authentication token.
	 * 
	 * @param token     the authentication token
	 * @param claimName the name of the claim to extract
	 * @return the extracted claim value, or null if the claim is not found
	 */
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Extracts all claims from the authentication token.
	 * 
	 * @param token the authentication token
	 * @return the extracted claims
	 * @see #extractClaim(String, Function)
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	/**
	 * Returns the secret key used for signing JWT tokens.
	 * 
	 * This method returns a secret key that is used to sign JWT tokens. The key is
	 * used to verify the authenticity of the token. The key is generated from the
	 * jwtSecret property, which is a base64-encoded string.
	 * 
	 * @return the secret key used for signing JWT tokens
	 * @see #jwtSecret
	 */
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
}
