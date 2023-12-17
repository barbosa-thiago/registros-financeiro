package com.example.sintegracebusca.config;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class RefererRedirectionAuthHandler extends SimpleUrlAuthenticationSuccessHandler
  implements AuthenticationSuccessHandler {

    public RefererRedirectionAuthHandler() {
            super();
            setUseReferer(true);
        }
}
