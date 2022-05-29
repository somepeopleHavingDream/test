package org.yangxin.test.proxy.cglib;

/**
 * 用户管理接口
 *
 * @author yangxin
 * 2022/1/26 17:46
 */
public interface UserManager {

    /**
     * 新增用户
     *
     * @param username 用户名
     * @param password 密码
     */
    void addUser(String username, String password);

    /**
     * 删除用户
     *
     * @param username 用户名
     */
    void deleteUser(String username);
}
