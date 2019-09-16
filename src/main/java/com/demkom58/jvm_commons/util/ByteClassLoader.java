package com.demkom58.jvm_commons.util;

public class ByteClassLoader extends ClassLoader {
    public Class define(String name, byte[] body) {
        return defineClass(name, body, 0, body.length);
    }
}
