package de.tse.spring.factorybeantest;

import de.tse.spring.factorybeantest.decorator.Decorator;
import de.tse.spring.factorybeantest.decorator.DecoratorFromAbstractFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotWorkingFactoryBeanConfig {

    @Bean public PrintService printService() throws Exception {
        return new PrintService(decorator());
    }

    @Bean public DecoratorFromAbstractFactoryBean decoratorFromAbstractFactoryBean() {
        return new DecoratorFromAbstractFactoryBean();
    }

    @Bean public Decorator decorator() throws Exception {
        //  If the DecoratorFactoryBean extends AbstractFactoryBean, the Decorator Interface can not be loaded by the ClassLoader
        return decoratorFromAbstractFactoryBean().getObject();
    }
}
