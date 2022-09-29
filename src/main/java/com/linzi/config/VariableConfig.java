package com.linzi.config;

import com.linzi.config.bean.Variable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class VariableConfig {

    private static Set<String> EXECUTOR_CLASS_INFOS = new HashSet<String>();
    private static Set<String> INCLUDE_CLASS_INFOS = new HashSet<String>();
    private static Set<String> EXECUTOR_END_CLASS_INFOS = new HashSet<String>();
    private static Set<String> INCLUDE_END_CLASS_INFOS = new HashSet<String>();


    static {


        EXECUTOR_END_CLASS_INFOS.add("BaseController");

        EXECUTOR_CLASS_INFOS.add("org.springframework");
        EXECUTOR_CLASS_INFOS.add("sun.net");
        EXECUTOR_CLASS_INFOS.add("org.apache");
        EXECUTOR_CLASS_INFOS.add("springfox.documentation");
        EXECUTOR_CLASS_INFOS.add("org.codehaus");
        EXECUTOR_CLASS_INFOS.add("com.example.thread_no_known.myresp");

        INCLUDE_CLASS_INFOS.add("com.example.thread_no_known");


        INCLUDE_END_CLASS_INFOS.add("Controller");

       ConfigProperties.init("variable.properties");

        String executorClassInfos = ConfigProperties.get("executor_class_infos");
        String includeClassInfos = ConfigProperties.get("include_class_infos");
        String executorEndClassInfos = ConfigProperties.get("executor_end_class_infos");
        String includeEndClassInfos = ConfigProperties.get("include_end_class_infos");

        if(isNotNull(executorClassInfos)){
            EXECUTOR_CLASS_INFOS.addAll(Arrays.asList(executorClassInfos.split(",")));
        }
        if(isNotNull(includeClassInfos)){
            INCLUDE_CLASS_INFOS.addAll(Arrays.asList(includeClassInfos.split(",")));
        }
        if(isNotNull(executorEndClassInfos)){
            EXECUTOR_END_CLASS_INFOS.addAll(Arrays.asList(executorEndClassInfos.split(",")));
        }
        if(isNotNull(includeEndClassInfos)){
            INCLUDE_END_CLASS_INFOS.addAll(Arrays.asList(includeEndClassInfos.split(",")));
        }
    }


    public static boolean isNotNull(String str) {
        if (str != null && str != "" && str.length() > 0) {
            return true;
        }
        return false;
    }

    public static Variable getVariable() {
        Variable variable = new Variable();
        variable.setExecutorClassInfos(EXECUTOR_CLASS_INFOS);
        variable.setIncludeClassInfos(INCLUDE_CLASS_INFOS);
        variable.setExecutorEndClassInfos(EXECUTOR_END_CLASS_INFOS);
        variable.setIncludeEndClassInfos(INCLUDE_END_CLASS_INFOS);
        return variable;
    }


}
