package com.sangs.plugin;

public class PluginResult {

    private boolean success;
    private Object data;

    public PluginResult(boolean defaultValue) {
        this.success = defaultValue;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
