package de.tse.spring.factorybeantest;

import de.tse.spring.factorybeantest.decorator.DecoratorFactoryBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Depending of the USE_CURLY_BACES Parameter, one of the the following Implementations
 *  has to be used:
 * <ol>
 *     <li>CurlyBracesDecorator</li>
 *     <li>SquareBracketDecorator</li>
 * </ol>
 *
 * It works if the custom FactoryBean implements the FactoryBean Interface. In case
 *  the custom FactoryBean extends AbstractFactoryBean, a BeanCreationException is
 *  thrown.
 */
public class FactoryBeanTest {

    @Test public void factoryImplementsFactoryBean() {

        System.setProperty(DecoratorFactoryBean.USE_CURLY_BRACES, "false");

        final ApplicationContext context = new AnnotationConfigApplicationContext(WorkingFactoryBeanConfig.class);

        final PrintService printService = context.getBean(PrintService.class);

        printService.print("Hallo");
    }

    @Test public void factoryExtendsAbstractFactoryBean() {

        System.setProperty(DecoratorFactoryBean.USE_CURLY_BRACES, "false");

        final ApplicationContext context = new AnnotationConfigApplicationContext(NotWorkingFactoryBeanConfig.class);

        final PrintService printService = context.getBean(PrintService.class);

        printService.print("Hallo");
    }
}
