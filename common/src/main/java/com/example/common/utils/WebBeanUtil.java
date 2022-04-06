package com.example.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author 代志豪
 * 2022/3/26 19:01
 */
public class WebBeanUtil {
    public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
        try {
            Field[] fields = beanClass.getDeclaredFields();
            Set<String> set = request.getParameterMap().keySet();
            Class<?>[] classes = Arrays.stream(fields).map(Field::getType).toArray(Class<?>[]::new);
            List<String> strings = new ArrayList<>(set);
            int length = strings.size();
            Object[] obj = new Object[length];
            for (int i = 0; i < length; i++) {
                obj[i] = request.getParameter(strings.get(i));
            }
            T t = BeanUtils.instantiateClass(beanClass.getConstructor(classes), obj);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
