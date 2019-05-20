package com.mazhangjing.server.config;

import java.util.List;

public class Config {
    private String version;
    private List<String> needRemovePathList;
    private String vmCheckCommand;
    private String vmCheckContains;
    private String vmRunCommand;
    private String vmRunContains;
    private Boolean ifCheckFailedThenLazyRun;
    private Integer retry;


    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    @Override
    public String toString() {
        return "Config{" +
                "version='" + version + '\'' +
                ", needRemovePathList=" + needRemovePathList +
                ", vmCheckCommand='" + vmCheckCommand + '\'' +
                ", vmCheckContains='" + vmCheckContains + '\'' +
                ", ifCheckFailedThenLazyRun=" + ifCheckFailedThenLazyRun +
                '}';
    }

    public String getVmRunCommand() {
        return vmRunCommand;
    }

    public void setVmRunCommand(String vmRunCommand) {
        this.vmRunCommand = vmRunCommand;
    }

    public String getVmRunContains() {
        return vmRunContains;
    }

    public void setVmRunContains(String vmRunContains) {
        this.vmRunContains = vmRunContains;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getNeedRemovePathList() {
        return needRemovePathList;
    }

    public void setNeedRemovePathList(List<String> needRemovePathList) {
        this.needRemovePathList = needRemovePathList;
    }

    public String getVmCheckCommand() {
        return vmCheckCommand;
    }

    public void setVmCheckCommand(String vmCheckCommand) {
        this.vmCheckCommand = vmCheckCommand;
    }

    public String getVmCheckContains() {
        return vmCheckContains;
    }

    public void setVmCheckContains(String vmCheckContains) {
        this.vmCheckContains = vmCheckContains;
    }

    public Boolean getIfCheckFailedThenLazyRun() {
        return ifCheckFailedThenLazyRun;
    }

    public void setIfCheckFailedThenLazyRun(Boolean ifCheckFailedThenLazyRun) {
        this.ifCheckFailedThenLazyRun = ifCheckFailedThenLazyRun;
    }

    public Config() {
    }
}
