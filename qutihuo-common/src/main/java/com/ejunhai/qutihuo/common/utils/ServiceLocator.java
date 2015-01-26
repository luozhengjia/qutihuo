package com.ejunhai.qutihuo.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

/**
 * 从spring容器中获取Bean
 * 
 * @author 罗正加
 * @date 2011-6-3
 * 
 */
@Service
public class ServiceLocator implements BeanFactoryAware {
	private static ServiceLocator serviceLocator = null;
	private static BeanFactory beanFactory = null;

	@SuppressWarnings("static-access")
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public static ServiceLocator getInstance() {
		if (serviceLocator == null)
			serviceLocator = (ServiceLocator) beanFactory.getBean("serviceLocator");
		return serviceLocator;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> c) {
		return (T) ServiceLocator.getInstance().getBeanFactory().getBean(c);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String c) {
		return (T) ServiceLocator.getInstance().getBeanFactory().getBean(c);
	}
}