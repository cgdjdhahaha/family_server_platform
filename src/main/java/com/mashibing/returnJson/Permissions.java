package com.mashibing.returnJson;

import java.util.List;

public class Permissions {
    private List<Permission> permissions;

    public Permissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Permissions{" +
            "permissions=" + permissions +
            '}';
    }
}
