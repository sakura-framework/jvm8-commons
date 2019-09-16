package com.demkom58.jvm_commons.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReflectionUtil {

    public static <OWNER> @Nullable Object getFieldValue(@NotNull final Class<OWNER> holder,
                                                         @NotNull final String fieldName,
                                                         @Nullable final OWNER instance) throws RuntimeException {
        try {
            Field field = holder.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(instance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull Collection<Object> getStaticFieldsValues(@NotNull final Class clazz) {
        List<Object> objects = new ArrayList<>();

        for (Field f : clazz.getDeclaredFields()) {
            if (String.class != f.getType())
                continue;

            f.setAccessible(true);
            try {
                objects.add(f.get(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return objects;
    }

}
