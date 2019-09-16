package com.demkom58.jvm_commons.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static <OWNER> Object getFieldValue(@NotNull final Class<OWNER> holder,
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

}
