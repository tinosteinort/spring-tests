package de.tse.spring.factorybeantest;

import de.tse.spring.factorybeantest.decorator.Decorator;
import de.tse.spring.factorybeantest.decorator.DecoratorFromAbstractFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotWorkingFactoryBeanConfig {

	@Bean
	public PrintService printService(Decorator decorator) throws Exception {
		return new PrintService(decorator);
	}

	@Bean
	public DecoratorFromAbstractFactoryBean decoratorFromAbstractFactoryBean() {
		DecoratorFromAbstractFactoryBean r = new DecoratorFromAbstractFactoryBean();
		return r;
	}

	/**
	 * CKL
	 * <ul>
	 * <li>Tried to upgrade to 4.2.4-BUILD-SNAPSHOT
	 * (https://jira.spring.io/browse/SPR-13696) but the error still occurred.
	 * </li>
	 * <li>Saw, that call to
	 * <em>decoratorFromAbstractFactoryBean()<--.getObject()</em> has been made.
	 * This will <strong>not</strong> return a Spring-managed factory bean.
	 * Because of this <em>AbstractFactoryBean::getEarlySingletonInstance {
	 * Proxy.newProxyInstance(...) }</em> failed. The classLoader parameter for
	 * <em>newInstance</em> has been null.as the class loader has no access to
	 * the de.tse package. I am not sure why the error directly happens when not
	 * using Spring instantiation. From my understanding newProxyInstance does
	 * allow null values and should fall back to the default class loader. There
	 * could be some problems with CGLIB but I am not sure with that.</li>
	 * <li>Error occurred in the first place by misusing Spring's mechanism.
	 * </li>
	 * </ul>
	 * 
	 * @param decoratorFromAbstractFactoryBean
	 *            factory bean must be manually injected so that
	 *            afterPropertiesSet() is called by Spring
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Decorator decorator(DecoratorFromAbstractFactoryBean decoratorFromAbstractFactoryBean) throws Exception {
		// FIX
		return decoratorFromAbstractFactoryBean/* Spring managed bean */.getObject();
	}
}
