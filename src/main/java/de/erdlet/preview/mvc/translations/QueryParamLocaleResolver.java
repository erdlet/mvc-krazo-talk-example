package de.erdlet.preview.mvc.translations;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.mvc.locale.LocaleResolver;
import javax.mvc.locale.LocaleResolverContext;
import java.util.Locale;

/**
 * Resolver to get the {@link Locale} to use from the requests query param.
 *
 * @author Tobias Erdle
 */
@Priority(1)
@ApplicationScoped
public class QueryParamLocaleResolver implements LocaleResolver {

  @Override
  public Locale resolveLocale(final LocaleResolverContext context) {
    final var queryLang = context.getUriInfo()
        .getQueryParameters()
        .getFirst("lang");
    return queryLang != null ? Locale.forLanguageTag(queryLang) : null;
  }
}
