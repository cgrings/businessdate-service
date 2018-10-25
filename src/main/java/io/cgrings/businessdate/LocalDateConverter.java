package io.cgrings.businessdate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class LocalDateConverter implements ParamConverterProvider {

    @Override public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

        if (!rawType.isAssignableFrom(LocalDate.class)) {
            return null;
        }

        return new ParamConverter<T>() {
            @Override
            @SuppressWarnings("unchecked")
            public T fromString(final String value) {
                if (value == null) {
                    throw new IllegalArgumentException("value may not be null");
                }
                return (T) LocalDate.parse(value);
            }

            @Override
            public String toString(final T value) {
                return ((LocalDate) value).format(DateTimeFormatter.ISO_LOCAL_DATE);
            }
        };
    }

}