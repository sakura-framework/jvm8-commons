package com.demkom58.jvm_commons.serialization.field;

/**
 * Field value hack interface.
 * Uses to get and set static private field values of object.
 * <p>
 * Is using in {@link StaticFieldAccessGenerator#generateIntAccessor(Class, String)} generate}
 * to return int boolean primitive-type.
 *
 * @author demkom58
 * @since 22.08.2019
 */
public interface IntStaticFieldAccessor {
    int get();
    void set(int value);
}
