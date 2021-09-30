package com.firebasky.lvbezjava.Fix;
import java.io.*;

public class Fix extends ObjectInputStream {
    public Fix(InputStream inputStream)
            throws IOException {
        super(inputStream);
    }
    /**
     * 只允许反序列化Cart.class
     */
    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException,
            ClassNotFoundException {
        if (desc.getName().equals(org.apache.commons.collections.Transformer.class.getName())) {
            throw new InvalidClassException(
                    "Unauthorized deserialization attempt",
                    desc.getName());
        }
        return super.resolveClass(desc);
    }
}
