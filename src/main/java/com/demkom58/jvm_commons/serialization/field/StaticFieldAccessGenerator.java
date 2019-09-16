package com.demkom58.jvm_commons.serialization.field;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import sun.reflect.MagicAccessorBridge;

/**
 * Field access generator, allows to access
 * static fields faster than reflection.
 * <p>
 * Generates access methods using ASM
 * code generation and some JVM tricks.
 *
 * @author demkom58
 * @since 22.08.2019
 */
public class StaticFieldAccessGenerator {

    @SuppressWarnings("unchecked")
    public <TYPE> ObjectStaticFieldAccessor<TYPE> generateObjectAccessor(@NotNull final Class owner,
                                                                         @NotNull final Class<TYPE> type,
                                                                         @NotNull final String fieldName) {
        try {
            return (ObjectStaticFieldAccessor<TYPE>) generateClass(owner, type, fieldName, ObjectStaticFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public BooleanStaticFieldAccessor generateBooleanAccessor(@NotNull final Class owner,
                                                              @NotNull final String fieldName) {
        try {
            return generateClass(owner, boolean.class, fieldName, BooleanStaticFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public IntStaticFieldAccessor generateByteAccessor(@NotNull final Class owner,
                                                       @NotNull final String fieldName) {
        try {
            return  generateClass(owner, byte.class, fieldName, IntStaticFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CharStaticFieldAccessor generateCharAccessor(@NotNull final Class owner,
                                                        @NotNull final String fieldName) {
        try {
            return generateClass(owner, char.class, fieldName, CharStaticFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ShortStaticFieldAccessor generateShortAccessor(@NotNull final Class owner,
                                                          @NotNull final String fieldName) {
        try {
            return generateClass(owner, short.class, fieldName, ShortStaticFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public IntStaticFieldAccessor generateIntAccessor(@NotNull final Class owner,
                                                      @NotNull final String fieldName) {
        try {
            return generateClass(owner, int.class, fieldName, IntStaticFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LongStaticFieldAccessor generateLongAccessor(@NotNull final Class owner,
                                                        @NotNull final String fieldName) {
        try {
            return generateClass(owner, int.class, fieldName, LongStaticFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Class<T> generateClass(@NotNull final Class owner,
                                       @NotNull final Class type,
                                       @NotNull final String fieldName,
                                       @NotNull final Class<T> returnInterface) {
        final String name = owner.getName().replace(".", "\\") + "-" + fieldName + "StaticAccessor";

        ClassWriter classWriter = new ClassWriter(0);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, name, null,
                Type.getInternalName(MagicAccessorBridge.class), new String[]{Type.getInternalName(returnInterface)});

        MethodVisitor methodVisitor;

        {
            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", "()V", false);
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }

        {
            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "get",
                    "()" + Type.getDescriptor(type.isPrimitive() ? type : Object.class), null, null);
            methodVisitor.visitCode();
            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, Type.getInternalName(owner), fieldName, Type.getDescriptor(type));
            methodVisitor.visitInsn(Opcodes.ARETURN);
            methodVisitor.visitMaxs(0, 2);
            methodVisitor.visitEnd();
        }

        {
            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "set",
                    "(" + Type.getDescriptor(type.isPrimitive() ? type : Object.class) + ")" + Type.getDescriptor(void.class), null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
            methodVisitor.visitFieldInsn(Opcodes.PUTSTATIC, Type.getInternalName(owner), fieldName, Type.getDescriptor(type));
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitMaxs(0, 3);
            methodVisitor.visitEnd();
        }


        classWriter.visitEnd();
        return FieldAccessGenerator.CLASS_LOADER.define(name, classWriter.toByteArray());
    }

}
