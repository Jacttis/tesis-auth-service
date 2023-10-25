package com.example.Auth.security;

import com.example.Auth.document.Client;
import com.example.Auth.document.Worker;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtToWorkerConvert implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt source) {
        Worker worker = new Worker();
        worker.setEmail(source.getSubject());
        return new UsernamePasswordAuthenticationToken(worker, source, Collections.EMPTY_LIST);
    }

}
