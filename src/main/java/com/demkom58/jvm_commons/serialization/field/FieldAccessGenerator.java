package com.demkom58.jvm_commons.serialization.field;

import com.demkom58.jvm_commons.util.AsmUtil;
import com.demkom58.jvm_commons.util.ByteClassLoader;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import sun.reflect.MagicAccessorBridge;

/**
 * Field access generator, allows to access
 * fields faster than reflection.
 * <p>
 * Generates access methods using ASM
 * code generation and some JVM tricks.
 *
 * @author demkom58
 * @since 22.08.2019
 */
public class FieldAccessGenerator {
    static final ByteClassLoader CLASS_LOADER = new ByteClassLoader();

    @SuppressWarnings("unchecked")
    public <OWNER, TYPE> ObjectFieldAccessor<OWNER, TYPE> generateObjectAccessor(@NotNull final Class<OWNER> owner, Class<TYPE> type,
                                                                                 @NotNull final String fieldName) {
        try {
            return (ObjectFieldAccessor<OWNER, TYPE>) generateClass(owner, type, fieldName, ObjectFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <OWNER> BooleanFieldAccessor<OWNER> generateBooleanAccessor(@NotNull final Class<OWNER> owner,
                                                                       @NotNull final String fieldName) {
        try {
            return generateClass(owner, boolean.class, fieldName, BooleanFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <OWNER> IntFieldAccessor<OWNER> generateByteAccessor(@NotNull final Class<OWNER> owner,
                                                                @NotNull final String fieldName) {
        try {
            return  generateClass(owner, byte.class, fieldName, IntFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <OWNER> CharFieldAccessor<OWNER> generateCharAccessor(@NotNull final Class<OWNER> owner,
                                                                 @NotNull final String fieldName) {
        try {
            return generateClass(owner, char.class, fieldName, CharFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <OWNER> ShortFieldAccessor<OWNER> generateShortAccessor(@NotNull final Class<OWNER> owner,
                                                                   @NotNull final String fieldName) {
        try {
            return generateClass(owner, short.class, fieldName, ShortFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <OWNER> IntFieldAccessor<OWNER> generateIntAccessor(@NotNull final Class<OWNER> owner,
                                                               @NotNull final String fieldName) {
        try {
            return generateClass(owner, int.class, fieldName, IntFieldAccessor.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <OWNER> LongFieldAccessor<OWNER> generateLongAccessor(@NotNull final Class<OWNER> owner,
                                                                 @NotNull final String fieldName) {
        try {
            return generateClass(owner, int.class, fieldName, LongFieldAccessor.class).newInstance();
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
        final String name = owner.getName().replace(".", "\\") + "#" + fieldName + "$LocalAccessor";

        try {
            return (Class<T>) CLASS_LOADER.loadClass(name);
        } catch (ClassNotFoundException ignored) { }

        ClassWriter classWriter = new ClassWriter(0);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, name, null,
                Type.getInternalName(MagicAccessorBridge.class), new String[]{Type.getInternalName(returnInterface)});

        MethodVisitor methodVisitor;

        {
            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);

            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", "()V", false);
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitMaxs(0, 2);
            methodVisitor.visitEnd();
        }

        {
            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "get",
                    "(" + Type.getDescriptor(Object.class) + ")" + Type.getDescriptor(type), null, null
            );

            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
            methodVisitor.visitFieldInsn(Opcodes.GETFIELD, Type.getInternalName(owner), fieldName, Type.getDescriptor(type));
            methodVisitor.visitInsn(AsmUtil.getReturn(type));
            methodVisitor.visitMaxs(1, 2);
            methodVisitor.visitEnd();
        }

        {
            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "set",
                    "(" + Type.getDescriptor(Object.class) + Type.getDescriptor(type) + ")"
                            + Type.getDescriptor(void.class), null, null
            );

            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 1);
            methodVisitor.visitVarInsn(AsmUtil.getLoad(type), 2);
            methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, Type.getInternalName(owner), fieldName, Type.getDescriptor(type));
            methodVisitor.visitInsn(Opcodes.RETURN);
            methodVisitor.visitMaxs(2, 3);
            methodVisitor.visitEnd();
        }

        classWriter.visitEnd();
        return CLASS_LOADER.define(name, classWriter.toByteArray());
    }

    public static ByteClassLoader getClassLoader() {
        return CLASS_LOADER;
    }
}
