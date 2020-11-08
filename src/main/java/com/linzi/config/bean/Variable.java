package com.linzi.config.bean;

import java.util.Set;

public class Variable {

    private Set<String> executorClassInfos;

    private Set<String> includeClassInfos;

    private Set<String> executorEndClassInfos;

    private Set<String> includeEndClassInfos;


    public Set<String> getExecutorClassInfos() {
        return executorClassInfos;
    }

    public void setExecutorClassInfos(Set<String> executorClassInfos) {
        this.executorClassInfos = executorClassInfos;
    }

    public Set<String> getIncludeClassInfos() {
        return includeClassInfos;
    }

    public void setIncludeClassInfos(Set<String> includeClassInfos) {
        this.includeClassInfos = includeClassInfos;
    }

    public Set<String> getExecutorEndClassInfos() {
        return executorEndClassInfos;
    }

    public void setExecutorEndClassInfos(Set<String> executorEndClassInfos) {
        this.executorEndClassInfos = executorEndClassInfos;
    }

    public Set<String> getIncludeEndClassInfos() {
        return includeEndClassInfos;
    }

    public void setIncludeEndClassInfos(Set<String> includeEndClassInfos) {
        this.includeEndClassInfos = includeEndClassInfos;
    }
}
