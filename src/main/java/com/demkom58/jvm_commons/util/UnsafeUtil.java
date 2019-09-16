package com.demkom58.jvm_commons.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {
    private static final Unsafe UNSAFE = (Unsafe) ReflectionUtil.getFieldValue(Unsafe.class, "theUnsafe", null);

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }

    public static void setStaticObject(@NotNull final Field field, @Nullable Object value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putObject(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field), value);
    }

    public static void setStaticBoolean(@NotNull final Field field, boolean value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putBoolean(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field), value);
    }

    public static void setStaticByte(@NotNull final Field field, byte value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putByte(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field), value);
    }

    public static void setStaticShort(@NotNull final Field field, short value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putShort(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field), value);
    }

    public static void setStaticInt(@NotNull final Field field, int value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putInt(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field), value);
    }

    public static void setStaticLong(@NotNull final Field field, long value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putLong(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field), value);
    }

    public static void setStaticFloat(@NotNull final Field field, float value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putFloat(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field), value);
    }

    public static void setStaticDouble(@NotNull final Field field, double value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putDouble(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field), value);
    }

    public static Object getStaticObject(@NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getObject(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));
    }

    public static boolean getStaticBoolean(@NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getBoolean(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));
    }

    public static byte getStaticByte(@NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getByte(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));
    }

    public static short getStaticShort(@NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getShort(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));
    }

    public static int getStaticInt(@NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getInt(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));
    }

    public static long getStaticLong(@NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getLong(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));
    }

    public static float getStaticFloat(@NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getFloat(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));
    }

    public static double getStaticDouble(@NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getDouble(unsafe.staticFieldBase(field), unsafe.staticFieldOffset(field));
    }

    public static void setObject(@NotNull final Object instance,
                                 @NotNull final Field field,
                                 @Nullable Object value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putObject(instance, unsafe.objectFieldOffset(field), value);
    }

    public static void setBoolean(@NotNull final Object instance,
                                  @NotNull final Field field,
                                  boolean value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putBoolean(instance, unsafe.objectFieldOffset(field), value);
    }

    public static void setByte(@NotNull final Object instance,
                               @NotNull final Field field,
                               byte value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putByte(instance, unsafe.objectFieldOffset(field), value);
    }

    public static void setShort(@NotNull final Object instance,
                                @NotNull final Field field,
                                short value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putShort(instance, unsafe.objectFieldOffset(field), value);
    }

    public static void setInt(@NotNull final Object instance,
                              @NotNull final Field field,
                              int value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putInt(instance, unsafe.objectFieldOffset(field), value);
    }

    public static void setLong(@NotNull final Object instance,
                               @NotNull final Field field,
                               long value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putLong(instance, unsafe.objectFieldOffset(field), value);
    }

    public static void setFloat(@NotNull final Object instance,
                                @NotNull final Field field,
                                float value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putFloat(instance, unsafe.objectFieldOffset(field), value);
    }

    public static void setDouble(@NotNull final Object instance,
                                 @NotNull final Field field,
                                 double value) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putDouble(instance, unsafe.objectFieldOffset(field), value);
    }

    public static Object getObject(@NotNull final Object instance, @NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getObject(instance, unsafe.objectFieldOffset(field));
    }

    public static boolean getBoolean(@NotNull final Object instance, @NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getBoolean(instance, unsafe.objectFieldOffset(field));
    }

    public static byte getByte(@NotNull final Object instance, @NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getByte(instance, unsafe.objectFieldOffset(field));
    }

    public static short getShort(@NotNull final Object instance, @NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getShort(instance, unsafe.objectFieldOffset(field));
    }

    public static int getInt(@NotNull final Object instance, @NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getInt(instance, unsafe.objectFieldOffset(field));
    }

    public static long getLong(@NotNull final Object instance, @NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getLong(instance, unsafe.objectFieldOffset(field));
    }

    public static float getFloat(@NotNull final Object instance, @NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getFloat(instance, unsafe.objectFieldOffset(field));
    }

    public static double getDouble(@NotNull final Object instance, @NotNull final Field field) {
        final Unsafe unsafe = UnsafeUtil.getUnsafe();
        return unsafe.getDouble(instance, unsafe.objectFieldOffset(field));
    }

}