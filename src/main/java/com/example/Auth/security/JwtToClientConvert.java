package com.example.Auth.security;

import com.example.Auth.document.Client;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtToClientConvert implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        Client client = new Client();
        client.setEmail(source.getSubject());
        return new UsernamePasswordAuthenticationToken(client, source, Collections.EMPTY_LIST);
    }

}
