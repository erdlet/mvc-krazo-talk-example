package de.erdlet.preview.mvc;

import java.util.Map;
import javax.mvc.security.Csrf;
import javax.mvc.security.Csrf.CsrfOptions;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/mvc")
public class App extends Application {
//
//  Enables IMPLICIT CSRF protection. This means, that the @CsrfProtected annotation
//  doesn't need to be set on controllers.
//
//  Nevertheless, the hidden field or HTTP header has to be added manually.
//
//  @Override
//  public Map<String, Object> getProperties() {
//    return Map.of(Csrf.CSRF_PROTECTION, CsrfOptions.IMPLICIT);
//  }
}
