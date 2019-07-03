package com.rjs.myshows.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties("com.rjs.myshows.server")
public class AppProperties {
	private String authName;
	private String authPass;
}
