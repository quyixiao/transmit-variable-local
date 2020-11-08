package com.test;


import java.util.HashSet;
import java.util.Set;

public class TestMain {


    private static Set<String> EXECUTOR_CLASS_NAMES = new HashSet<String>();
    private static Set<String> INCLUDE_CLASS_NAMES = new HashSet<String>();

    static {
        EXECUTOR_CLASS_NAMES.add("BaseContoller");
        INCLUDE_CLASS_NAMES.add("Contoller");
    }

    public static void main(String[] args) {
        String className = "AppContoller";

        if (isNotNull(className) && contains(INCLUDE_CLASS_NAMES, className)
                && !contains(EXECUTOR_CLASS_NAMES, className)) {
            System.out.println("执行");
        }

        System.out.println("111");
    }


    public static boolean isNotNull(String str) {
        if (str != null && str != "") {
            return true;
        }
        return false;
    }

    public static boolean contains(Set<String> includeClassNames, String className) {
        for (String include : includeClassNames) {
            if (className.contains(include)) {
                return true;
            }
        }
        return false;
    }


}
