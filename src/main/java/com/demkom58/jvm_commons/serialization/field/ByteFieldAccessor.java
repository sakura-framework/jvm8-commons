package com.demkom58.jvm_commons.serialization.field;

import org.jetbrains.annotations.NotNull;

/**
 * Field value hack interface.
 * Uses to get and set private field values of object.
 * <p>
 * Is using in {@link FieldAccessGenerator#generateByteAccessor(Class, String)} generate}
 * to return byte primitive-type.
 *
 * @author demkom58
 * @since 22.08.2019
 */
public interface ByteFieldAccessor<OWNER> {
    byte get(@NotNull final OWNER instance);
    void set(@NotNull final OWNER instance, byte value);
}
