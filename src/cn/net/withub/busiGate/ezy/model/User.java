package cn.net.withub.busiGate.ezy.model;

import cn.net.withub.busiGate.loginInfo.LoginInfo;

public class User extends LoginInfo {

    private String username;

    private String roleTag;

    private String organizationCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleTag() {
        return roleTag;
    }

    public void setRoleTag(String roleTag) {
        this.roleTag = roleTag;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
}
