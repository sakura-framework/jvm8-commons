package com.demkom58.jvm_commons.serialization.field;

/**
 * Field value hack interface.
 * Uses to get and set static private field values of object.
 * <p>
 * Is using in {@link StaticFieldAccessGenerator#generateObjectAccessor(Class, Class, String)} generate}
 * to return object and array types.
 *
 * @author demkom58
 * @since 22.08.2019
 */
public interface ObjectStaticFieldAccessor<TYPE> {
    TYPE get();
    void set(TYPE value);
}
