package com.apryshchepa.taskdiff.fxml;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class SpringFXMLLoader<S> {

    private ApplicationContext applicationContext;

    public SpringFXMLLoader(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public S load(URL location) throws IOException {
        if (location == null) {
            throw new IllegalArgumentException("Location can't be null");
        }

        FXMLLoader fxmlLoader = createLoader(location);
        return fxmlLoader.load();
    }

    private FXMLLoader createLoader(URL location) {
        FXMLLoader loader = new FXMLLoader();
        loader.setCharset(Charset.defaultCharset());
        if (location != null) {
            loader.setLocation(location);
        }
        loader.setControllerFactory(applicationContext::getBean);
        loader.setBuilderFactory(getBuidlerFactory());
        return loader;
    }

    private BuilderFactory getBuidlerFactory() {
        return new BuilderFactory() {
            JavaFXBuilderFactory defaultFactory = new JavaFXBuilderFactory();

            @Override
            public Builder<?> getBuilder(Class<?> type) {
                String[] beanNames = applicationContext.getBeanNamesForType(type);
                if (beanNames.length == 1) {
                    return (Builder<Object>) () -> applicationContext.getBean(beanNames[0]);
                } else {
                    return defaultFactory.getBuilder(type);
                }
            }
        };
    }
}