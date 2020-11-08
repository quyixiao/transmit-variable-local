package com.linzi.thread.agent.logging;

import javassist.bytecode.LocalVariableAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * logger adaptor for ttl java agent, internal use for ttl usage only!
 *
 * @author Jerry Lee (oldratlee at gmail dot com)
 * @since 2.6.0
 */
public abstract class Logger {
    public static final String TTL_AGENT_LOGGER_KEY = "ttl.agent.logger";
    public static final String STDOUT = "STDOUT";
    public static final String STDERR = "STDERR";

    private static volatile int loggerImplType = -1;

    public static void setLoggerImplType(String type) {
        if (loggerImplType != -1) {
            throw new IllegalStateException("TTL logger implementation type is already set! type = " + loggerImplType);
        }

        if (STDERR.equalsIgnoreCase(type)) {
            loggerImplType = 0;
        } else if (STDOUT.equalsIgnoreCase(type)) {
            loggerImplType = 1;
        } else {
            loggerImplType = 0;
        }
    }

    public static Logger getLogger(Class<?> clazz) {
        if (loggerImplType == -1) {
            throw new IllegalStateException("TTL logger implementation type is NOT set!");
        }

        switch (loggerImplType) {
            case 1:
                return new StdOutLogger(clazz);
            default:
                return new StdErrorLogger(clazz);
        }
    }

    final Class<?> logClass;

    private Logger(Class<?> logClass) {
        this.logClass = logClass;
    }

    public void info(String msg) {
        log(Level.INFO, msg, null);
    }

    public abstract void log(Level level, String msg, Throwable thrown);

    public void error(String s, int size) {
        error(s, size);
    }

    public void warn(String s, String name, String longName, LocalVariableAttribute localVariableAttribute, int i, int length, boolean aStatic) {
        warn(s, name, longName, localVariableAttribute, i, length, aStatic);
    }

    public void trace(String s, String name, int slot, String sig, String localVarDescriptor) {
        trace(s, name, slot, s, localVarDescriptor);
    }

    private static class StdErrorLogger extends Logger {
        StdErrorLogger(Class<?> clazz) {
            super(clazz);
        }

        @Override
        public void log(Level level, String msg, Throwable thrown) {
            if (level == Level.SEVERE) {
                final String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
                System.err.printf("%s %s [%s] %s: %s%n", time, level, Thread.currentThread().getName(), logClass.getSimpleName(), msg);
                if (thrown != null) thrown.printStackTrace();
            }
        }

        @Override
        public void error(String msg, int size) {
            final String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
            System.err.printf("%s %s [%s] %s: %s%s%n", time, "error", Thread.currentThread().getName(), logClass.getSimpleName(), msg, size);
        }
    }

    private static class StdOutLogger extends Logger {
        StdOutLogger(Class<?> clazz) {
            super(clazz);
        }

        @Override
        public void log(Level level, String msg, Throwable thrown) {
            final String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
            System.out.printf("%s %s [%s] %s: %s%n", time, level, Thread.currentThread().getName(), logClass.getSimpleName(), msg);
            if (thrown != null) thrown.printStackTrace(System.out);
        }

        @Override
        public void warn(String msg, String name, String longName, LocalVariableAttribute localVariableAttribute, int i, int length, boolean aStatic) {
            final String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
            System.out.printf("%s %s [%s] %s: %s%n", time, "warn", Thread.currentThread().getName(), logClass.getSimpleName(), msg);
        }

        @Override
        public void trace(String msg, String name, int slot, String sig, String localVarDescriptor) {
            final String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
            System.out.printf("%s %s [%s] %s: %s%n", time, "trace", Thread.currentThread().getName(), logClass.getSimpleName(), msg);
        }
    }
}
