package com.ggomzirak.service;

import org.springframework.stereotype.Component;

import com.ggomzirak.db.entity.User;
import com.ggomzirak.db.repository.UserRepository;
import com.ggomzirak.dto.Token;
import com.ggomzirak.dto.UserResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTService {

	private static final Logger log = LoggerFactory.getLogger(JWTService.class);

	@Value("${jwt.salt}")
	private String salt;

	@Value("${jwt.expmin}")
	private Long expireMin;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRepository UserRepository;

	public Token create(User userInfo) {
		
		UserResponse userRes = new UserResponse();
		userRes.setId(userInfo.getId());
		userRes.setEmail(userInfo.getEmail());
		userRes.setImage(userInfo.getImage());
		userRes.setLevel(userInfo.getLevel());
		userRes.setName(userInfo.getName());
		userRes.setUserid(userInfo.getUserId());
		userRes.setPoints(userInfo.getPoints());
		// Create Access Token
		String accessJwt = createJws(salt, expireMin, userRes);

		// Create Refresh Token
		String refreshJwt = createJws("MYSALTMYSALTMYSALTMYSALTMYSALTMYSALT", (long) 10080, null);

		Token tokens = new Token();
		tokens.setAccessJwt(accessJwt);
		tokens.setRefreshJwt(refreshJwt);

		User User = UserRepository.findByUserId(userInfo.getUserId());
		User.setRefreshtoken(refreshJwt);
		UserRepository.save(User);
		return tokens;

	}

	public String createJws(String encodekey, Long expMin, final UserResponse User) {
		log.trace("time:{}", expMin);
		JwtBuilder builder = Jwts.builder();

		builder.setHeaderParam("typ", "JWT");

		builder.setSubject("로그인토큰").setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expMin))
				.claim("User", User);

		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());

		final String jwt = builder.compact();
		log.debug("토큰 발행:{}", jwt);

		return jwt;
	}

	public boolean checkValid(final String jwt) {
		log.trace("토큰 점검:{}", jwt);
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
			System.out.println(claims.getBody().get("User"));
			System.out.println(claims.getBody().getExpiration());
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	public Map<String, Object> get(final String jwt) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch (final Exception e) {
			throw new RuntimeException();
		}

		log.trace("claims:{}", claims);
		return claims.getBody();
	}

	public User getUserByToken(String refreshtoken) {
		return UserRepository.findByRefreshtoken(refreshtoken);
	}
}
