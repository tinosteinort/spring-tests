package de.tse.spring.factorybeantest;

import de.tse.spring.factorybeantest.decorator.Decorator;

public class PrintService {

    private Decorator decorator;

    public PrintService(final Decorator decorator) {
        this.decorator = decorator;
    }

    public void print(final String value) {
        System.out.println(decorator.decorate(value));
    }
}
