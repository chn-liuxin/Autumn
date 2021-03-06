package edu.cuit.autumn.entity;

public class User {
    /**
     * @Param userId：用户id
     * @Param userIdentity：用户身份标识
     * @Param userName：用户名
     * @Param userPassword：用户密码
     */
    private String userId;

    private Short userIdentity;

    private String userName;

    private String userPassword;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Short getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(Short userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    @Override
    public String toString() {
        return userId + "\t" + userIdentity + "\t" + userName + "\t" + userPassword;
    }
}