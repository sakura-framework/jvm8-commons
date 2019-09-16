package com.demkom58.jvm_commons.serialization.field;

/**
 * Field value hack interface.
 * Uses to get and set static private field values of object.
 * <p>
 * Is using in {@link StaticFieldAccessGenerator#generateCharAccessor(Class, String)} generate}
 * to return char primitive-type.
 *
 * @author demkom58
 * @since 22.08.2019
 */
public interface CharStaticFieldAccessor {
    char get();
    void set(char value);
}
