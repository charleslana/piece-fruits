package com.charles.games.piecefruits.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class PropertiesConfig {
    @Value("${secret.key}")
    private String secret;
}
