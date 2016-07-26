package de.tse.spring.factorybeantest.decorator;

public class SquareBracketDecorator implements Decorator {

    @Override public String decorate(final String value) {
        return "[" + value + "]";
    }
}
