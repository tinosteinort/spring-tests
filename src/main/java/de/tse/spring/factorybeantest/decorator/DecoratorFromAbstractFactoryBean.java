package de.tse.spring.factorybeantest.decorator;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import static de.tse.spring.factorybeantest.decorator.DecoratorFactoryBean.USE_CURLY_BRACES;

public class DecoratorFromAbstractFactoryBean extends AbstractFactoryBean<Decorator> {

    @Override protected Decorator createInstance() throws Exception {
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
}
