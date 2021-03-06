package com.linzi.thread.agent;


import com.linzi.thread.agent.logging.Logger;
import com.linzi.thread.agent.transformlet.JavassistTransformlet;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

/**
 * TTL {@link ClassFileTransformer} of Java Agent
 *
 * @author Jerry Lee (oldratlee at gmail dot com)
 * @see ClassFileTransformer
 * @see <a href="https://docs.oracle.com/javase/10/docs/api/java/lang/instrument/package-summary.html">The mechanism for instrumentation</a>
 * @since 0.9.0
 */
public class TtlTransformer implements ClassFileTransformer {
    private static final Logger logger = Logger.getLogger(TtlTransformer.class);


    private static final byte[] EMPTY_BYTE_ARRAY = {};

    private final List<JavassistTransformlet> transformletList = new ArrayList<JavassistTransformlet>();

    TtlTransformer(List<Class<? extends JavassistTransformlet>> transformletClasses) throws Exception {
        for (Class<? extends JavassistTransformlet> transformletClass : transformletClasses) {
            final JavassistTransformlet transformlet = transformletClass.getConstructor().newInstance();
            transformletList.add(transformlet);
            logger.info("[TtlTransformer] add Transformlet " + transformletClass + " success");
        }
    }

    @Override
    public final byte[] transform(final ClassLoader loader, final String classFile, final Class<?> classBeingRedefined,
                                  final ProtectionDomain protectionDomain, final byte[] classFileBuffer) {
        try {
            // Lambda has no class file, no need to transform, just return.
            if (classFile == null) {
                return EMPTY_BYTE_ARRAY;
            }
            final String className = toClassName(classFile);

            for (JavassistTransformlet transformlet : transformletList) {
                final byte[] bytes = transformlet.doTransform(className, classFileBuffer, loader);
                if (bytes != null) return bytes;
            }

        } catch (Throwable t) {
            String msg = "Fail to transform class " + classFile + ", cause: " + t.toString();
            throw new IllegalStateException(msg, t);
        }

        return EMPTY_BYTE_ARRAY;
    }

    private static String toClassName(final String classFile) {
        return classFile.replace('/', '.');
    }
}
