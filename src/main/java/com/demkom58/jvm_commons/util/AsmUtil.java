package com.demkom58.jvm_commons.util;


import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;

public class AsmUtil {
    public static int getReturn(@NotNull Class clazz) {
        if (clazz == boolean.class || clazz == byte.class || clazz == short.class
                || clazz == char.class || clazz == int.class)
            return Opcodes.IRETURN;

        if (clazz == void.class)
            return Opcodes.RETURN;

        if (clazz == double.class)
            return Opcodes.DRETURN;

        if (clazz == float.class)
            return Opcodes.FRETURN;

        if (clazz == long.class)
            return Opcodes.LRETURN;

        return Opcodes.ARETURN;
    }

    public static int getLoad(@NotNull Class clazz) {
        if (clazz == boolean.class || clazz == byte.class || clazz == short.class
                || clazz == char.class || clazz == int.class)
            return Opcodes.ILOAD;

        if (clazz == double.class)
            return Opcodes.DLOAD;

        if (clazz == float.class)
            return Opcodes.FLOAD;

        if (clazz == long.class)
            return Opcodes.LLOAD;

        if (clazz == void.class)
            throw new UnsupportedOperationException();

        return Opcodes.ALOAD;
    }
}
