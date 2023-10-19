package com.tube.utube.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(properties = {
        "spring.security.oauth2.resourceserver.jwt.issuer-uri=https://dev-5adwy3727eoci2nk.us.auth0.com/",
        "auth0.audience=http://localhost:8080/"
})
class SecurityConfigTest {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Value("${auth0.audience}")
    private String audience;
    private NimbusJwtDecoder jwtDecoder;

    @BeforeEach
    void setUp() {
        jwtDecoder = NimbusJwtDecoder.withJwkSetUri(issuer + ".well-known/jwks.json").build();

    }

    @Test
    void jwtDecoderTest() {
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1OMV96aFJZSC03ZkFmX2V2aTg1RSJ9.eyJpc3MiOiJodHRwczovL2Rldi01YWR3eTM3Mjdlb2NpMm5rLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJxc1FMSktzZEhoUXAwVUhaWU96c1FwM2lVY2ljdG4xTEBjbGllbnRzIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiaWF0IjoxNjk2MDE1NzA3LCJleHAiOjE2OTYxMDIxMDcsImF6cCI6InFzUUxKS3NkSGhRcDBVSFpZT3pzUXAzaVVjaWN0bjFMIiwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIn0.AuIbxifeeLsAxJEs5YGOt30VNPVY9kuL2nhnoOUnpwhOnDn5tEV_Gia7hr2bZtOm5YQD6Ox2ge2cjdNYnKhrG_2mclJk-vHqCEUW_TK-cULU6m1t_vT23mKp8aVC2o1sd-innBm5JT21aUFuctAi5rxnzX23EFvnNnB-pJZJk-85HPAsYF-A4LU2mJgk2-IJ8-OEA1TlDMIXHVEkiR93cUIBga8n6Kzg6f3uCChQblcVT4iSs9nkgjm2nxwTVljPDf694JpzoXCxutljXlFKQPc1ba1W8OBdjX__3CImWRDUyjWPxZEO3q6nUJYQhlDqrVa4Ibx48iQHYfxebMbObQ";
        Jwt jwt = jwtDecoder.decode(token);

        assertNotNull(jwt);
    }
}