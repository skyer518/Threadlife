package cn.com.u2be.threadlife.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 明 on 2015/11/29.
 */

/**
 * 反射的util类.
 *
 * @author springside
 */
public class ReflectUtil {
    private static final Logger log = LoggerFactory.getLogger(ReflectUtil.class);

    private ReflectUtil() {
    }

    public static Class<?> getGenericParameterType(Class<?> clazz) {
        //返回表示此Class所表示的实体（类、接口、基本类型或 void）的直接超类的Type。如果超类是参数化类型，则返回的Type对象必须准确反映源代码中所使用的实际类型参数。
        Type type = clazz.getGenericSuperclass();
        //强制转化为参数化类型：Collection<String>
        ParameterizedType pt = (ParameterizedType) type;
        //返回表示此类型的实际类型参数的 Type 对象的数组。
        Type[] types = pt.getActualTypeArguments();
        return (Class<?>) types[0];
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends GenricManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or <code>Object.class</code> if cannot be determined
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
                    + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class) params[index];
    }
}