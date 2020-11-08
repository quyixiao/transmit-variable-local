package com.linzi;

import com.linzi.classloading.enhancers.LocalvariablesNamesEnhancer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RenderArgs {

    public static ThreadLocal<Map<String, Object>> current = new ThreadLocal<Map<String, Object>>();

    public RenderArgs() {

    }

    public static void put(String key, Object arg) {
        if(current.get() == null){
            Map<String,Object> data = new HashMap<String,Object>();
            data.put(key,arg);
            current.set(data);
        }else{
            current.get().put(key,arg);
        }
    }
    public static Object get(String key) {
        return current;
    }



    public static Map<String, Object> toMap(Object... args) {
        Map<String, Object> result = null;
        try {
            result = new HashMap(16);
            Object[] var6 = args;
            int var5 = args.length;
            for (int var4 = 0; var4 < var5; ++var4) {
                Object o = var6[var4];
                List<String> names = LocalvariablesNamesEnhancer.LocalVariablesNamesTracer.getAllLocalVariableNames(o);
                Iterator var9 = names.iterator();
                while (var9.hasNext()) {
                    String name = (String) var9.next();
                    result.put(name, o);
                }
            }
            if(RenderArgs.current != null
                    && RenderArgs.current.get() != null
                    && !RenderArgs.current.get().isEmpty()){
                for (Map.Entry<String, Object> entry : RenderArgs.current.get().entrySet()) {
                    result.put(entry.getKey(),entry.getValue());
                }
            }
        } catch (Exception e) {
            System.out.println("ServletUtils->toMap is exception");
        } finally {
            //清空
            if(RenderArgs.current != null && RenderArgs.current.get() != null   ){
                RenderArgs.current.get().clear();
            }
        }
        return result;
    }


}
