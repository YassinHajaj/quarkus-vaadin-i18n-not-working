import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.quarkus.annotation.VaadinServiceEnabled;
import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.FRANCE;
import static java.util.Locale.forLanguageTag;

@Unremovable
@ApplicationScoped
@VaadinServiceEnabled
public class I18n implements I18NProvider {

    private static final Locale DUTCH = forLanguageTag("nl");

    private static final Logger LOGGER = Logger.getLogger(I18n.class.getName());

    @Override
    public List<Locale> getProvidedLocales() {
        return List.of(FRANCE, ENGLISH, DUTCH);
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        try {
            var bundle = ResourceBundle.getBundle("vaadin-i18n/translations", locale);
            return bundle.getString(key);
        } catch (MissingResourceException missingResourceException) {
            LOGGER.warning("Missing resource bundle for locale " + locale + ". Falling back to English.");
            var bundle = ResourceBundle.getBundle("vaadin-i18n/translations", ENGLISH);
            return bundle.getString(key);
        }
    }
}
