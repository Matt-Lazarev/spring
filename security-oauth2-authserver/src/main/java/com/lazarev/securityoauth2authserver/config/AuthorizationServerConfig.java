package com.lazarev.securityoauth2authserver.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig {
    //verifier = 123
    //challenge = pmWkWSBCL51Bfkhn79xPuKBKHz__H6B-mY6G9_eieuM

    /*
    authorization:
    http://localhost:9000/oauth2/authorize
        ?response_type=code
        &client_id=client
        &scope=openid
        &redirect_uri=https://springone.io/authorized
        &code_challenge=ed407d245eaafb5aa914220fe35f1ce3fd57bcd1b1ed890b266948586c58d7db
        &code_challenge_method=S256

     Request:
     http://localhost:9000/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=https://springone.io/authorized&code_challenge=pmWkWSBCL51Bfkhn79xPuKBKHz__H6B-mY6G9_eieuM&code_challenge_method=S256

     Redirect:
     https://springone.io/authorized?code=ACZmUZH74DMYMZcx8UI1M6s6dIMm2ITv7Ik-saHdpYFbR99vMkIjr2se7QIhFSwswWW5uH9ZSCqZXKnUuNoV5s7VRHc0ZPGeLWxO5E3s-j4fqWyiTvO6mYGl0Z8cXzZn
     */


    /*
    token:
    http://localhost:9000/oauth2/token
        ?client_id=client
        &redirect_uri=https://springone.io/authorized
        &grant_type=authorization_code
        &code=18-ej-DtdZ5VFitv2pBCsjqo15lWtsYpPEsa--   bRHpo2P3MIRLYWfavZ88Zcints4hYhgS07obr-JlVosc1gUNuCietRuMk930CYOtYFG85xY2qn0G53uslqXkn0_HDs
        &code_verifier=123

     Response: JSON / Access Token, Refresh Token, ID Token
     */

    /*
    introspect:
    http://localhost:9000/oauth2/introspect
        ?token=eyJraWQiOiI3YjdjMDY3Yi00OGZjLTQwMWMtYWI2Mi03ZTcyNjMyMGI3MzMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXVkIjoiY2xpZW50IiwibmJmIjoxNjcyODUzMTk4LCJzY29wZSI6WyJvcGVuaWQiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNjcyODUzNDk4LCJpYXQiOjE2NzI4NTMxOTh9.eVuIPm75UQ0ums8CPRCmkxyepzV0VUmMPK_VdenaP2vArXJu4oV6gmhoU19KyRKSInSMm1RWJNAoSB3kbLKqYV6pO1xIUwckL16iDjD_Gn663_SMKed4OKayb-ENc9ROaVMHDuyX3gq5rAIZ3AAWR6niWZ8LYwqd5W5JREEBSjaUIGHfvdg-wVztn_dwxE0bd3pc-4thW-4TxOai7AdImWALW7vV3VpZyxBsAHB03eNm4rbBO-7xHcs-15MbIlKRNV56CjYTRhgHR616V74vbGVSBva9hwAQpYcuHbagcygKc0ftw16kepP-bRphZ92_HGjV18tLnZxH7WZcLjlpxg
     */
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain asSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                    .oidc(Customizer.withDefaults())
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
        return http.build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
//        RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("client")
//                .clientSecret(passwordEncoder.encode("secret"))
//                .scope(OidcScopes.OPENID)
//                .redirectUri("http://127.0.0.1:8080/authorized")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .build();

        RegisteredClientRepository repository = new JdbcRegisteredClientRepository(jdbcTemplate);
        //repository.save(client);
        return repository;
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(2048);
        KeyPair kp = kg.generateKeyPair();

        RSAKey key = new RSAKey.Builder((RSAPublicKey) kp.getPublic())
                .privateKey((RSAPrivateKey) kp.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet set = new JWKSet(key);
        return new ImmutableJWKSet<>(set);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            if (context.getTokenType() == OAuth2TokenType.ACCESS_TOKEN) {
                Authentication principal = context.getPrincipal();
                Set<String> authorities = principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet());
                context.getClaims().claim("roles", authorities);
            }
        };
    }
}
