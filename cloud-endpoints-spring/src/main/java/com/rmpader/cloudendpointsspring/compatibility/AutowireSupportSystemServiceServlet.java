package com.rmpader.cloudendpointsspring.compatibility;

import com.google.api.server.spi.SystemServiceServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.logging.Logger;

public class AutowireSupportSystemServiceServlet extends SystemServiceServlet {

    private static final Logger LOGGER = Logger.getLogger(AutowireSupportSystemServiceServlet.class.getName());

    @Override
    protected <T> T createService(Class<T> serviceClass) {
        ApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(this.getServletContext());
        AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
        try {
            return beanFactory.getBean(serviceClass);
        } catch (BeansException e) {
            String beanName = StringUtils.uncapitalize(serviceClass.getSimpleName());
            LOGGER.info("No bean of type'" + serviceClass.getName() + "'." +
                                " Creating a new bean named '" + beanName + "'");
            T bean = (T) beanFactory.createBean(serviceClass, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
            return (T) beanFactory.initializeBean(bean, beanName);
        }
    }
}
