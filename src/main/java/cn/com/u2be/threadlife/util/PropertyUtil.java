package cn.com.u2be.threadlife.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by Ã÷ on 2015/11/29.
 */
public class PropertyUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);

    /**
     * get Filed value form entity
     *
     * @param entity
     * @param name
     * @param <T>
     * @return
     */
    public static <T> Object getProperty(T entity, String name) {

        try {
            Field declaredField = entity.getClass().getDeclaredField(name);
            return declaredField.get(entity);
        } catch (Exception e) {
            LOGGER.error("Class does not declared Field", e);
            throw new RuntimeException(e);
        }

    }
}
