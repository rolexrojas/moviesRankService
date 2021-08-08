package com.backbase.moviesRankService.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ApplicationProperties {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;
    @Value("${spring.datasource.username}")
    private String dataSourceUsername;
    @Value("${spring.sql.init.platform}")
    private String databasePlatform;
    @Value("${spring.datasource.password}")
    private String dataSourcePassword;
    @Value("${spring.datasource.driver-class-name}")
    private String datasourceDriverClassName;
    @Value("${server.port}")
    private String serverPort;
    @Value("${time.expiration.token}")
    private String timeExpirationToken;
    @Value("${token.secret}")
    private String tokenSecret;
    @Value("${omdb.url}")
    private String omdbUrl;
    @Value("${omdb.apikey}")
    private String omdbApikey;


    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    public void setDataSourceUrl(String dataSourceUrl) {
        this.dataSourceUrl = dataSourceUrl;
    }

    public String getDataSourceUsername() {
        return dataSourceUsername;
    }

    public void setDataSourceUsername(String dataSourceUsername) {
        this.dataSourceUsername = dataSourceUsername;
    }

    public String getDatabasePlatform() {
        return databasePlatform;
    }

    public void setDatabasePlatform(String databasePlatform) {
        this.databasePlatform = databasePlatform;
    }

    public String getDataSourcePassword() {
        return dataSourcePassword;
    }

    public void setDataSourcePassword(String dataSourcePassword) {
        this.dataSourcePassword = dataSourcePassword;
    }

    public String getDatasourceDriverClassName() {
        return datasourceDriverClassName;
    }

    public void setDatasourceDriverClassName(String datasourceDriverClassName) {
        this.datasourceDriverClassName = datasourceDriverClassName;
    }

    public String getTimeExpirationToken() {
        return timeExpirationToken;
    }

    public void setTimeExpirationToken(String timeExpirationToken) {
        this.timeExpirationToken = timeExpirationToken;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public String getOmdbUrl() {
        return omdbUrl;
    }

    public void setOmdbUrl(String omdbUrl) {
        this.omdbUrl = omdbUrl;
    }

    public String getOmdbApikey() {
        return omdbApikey;
    }

    public void setOmdbApikey(String omdbApikey) {
        this.omdbApikey = omdbApikey;
    }
}
