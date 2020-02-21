package br.org.pti.compras.application.i18n;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Properties;

public class ResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    public ResourceBundleMessageSource() {
        MessageSourceHolder.setMessageSource(this);
    }

    public Properties getProperties(Locale locale) {
        super.clearCacheIncludingAncestors();

        final PropertiesHolder propertiesHolder = super.getMergedProperties(locale);
        return propertiesHolder.getProperties();
    }
}
