package com.rock.power.secondhand.server.model.mysql;

/**
 * Created by yanshi on 16/9/6.
 */
public class Version {
    private Integer version;
    private String type;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
