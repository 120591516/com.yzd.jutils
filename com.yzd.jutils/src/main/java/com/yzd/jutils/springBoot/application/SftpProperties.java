package com.yzd.jutils.springBoot.application;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sftp.client")
public class SftpProperties {
	private String host;

	private Integer port;

	private String protocol;

	private String username;

	private String password;

	private String root;

	private String privateKey;

	private String passphrase;

	private String sessionStrictHostKeyChecking;

	private Integer sessionConnectTimeout;

	private Integer channelConnectedTimeout;
}