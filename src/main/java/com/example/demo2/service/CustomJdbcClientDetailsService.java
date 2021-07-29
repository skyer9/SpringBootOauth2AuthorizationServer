package com.example.demo2.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Service
public class CustomJdbcClientDetailsService extends JdbcClientDetailsService {

    public CustomJdbcClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value="clientId")
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        // System.out.println("1111111111111111111");
        return super.loadClientByClientId(clientId);
    }

    @Override
    @Transactional
    @CacheEvict(value="clientId", key="#clientDetails.getClientId()")
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        super.updateClientDetails(clientDetails);
    }

    @Override
    @Transactional
    @CacheEvict(value="clientId", key="#clientId")
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        super.updateClientSecret(clientId, secret);
    }

    @Override
    @Transactional
    @CacheEvict(value="clientId")
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        super.removeClientDetails(clientId);
    }
}
