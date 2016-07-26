package de.tse.spring.factorybeantest;

import de.tse.spring.factorybeantest.decorator.Decorator;
import de.tse.spring.factorybeantest.decorator.DecoratorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkingFactoryBeanConfig {

    @Bean public PrintService printService() throws Exception {
        return new PrintService(decorator());
    }

    @Bean public DecoratorFactoryBean decoratorFactoryBean() {
        return new DecoratorFactoryBean();
    }

    @Bean public Decorator decorator() throws Exception {
        // If the DecoratorFactoryBean implements the FactoryBean Interface, everything is OK
        return decoratorFactoryBean().getObject();
    }
}
