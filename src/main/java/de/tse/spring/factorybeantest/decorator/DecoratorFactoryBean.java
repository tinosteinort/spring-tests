package de.tse.spring.factorybeantest.decorator;

import org.springframework.beans.factory.FactoryBean;

public class DecoratorFactoryBean implements FactoryBean<Decorator> {

    public static final String USE_CURLY_BRACES = "USE_CURLY_BRACES";

    @Override public Decorator getObject() throws Exception {
        if (useCurlyBraces()) {
            return new CurlyBracesDecorator();
        }
        return new SquareBracketDecorator();
    }

    private boolean useCurlyBraces() {
        if (System.getProperty(USE_CURLY_BRACES) == null) {
            return false;
        }
        return "true".equalsIgnoreCase(System.getProperty(USE_CURLY_BRACES));
    }

    @Override public Class<?> getObjectType() {
        return Decorator.class;
    }

    @Override public boolean isSingleton() {
        return true;
    }
}
