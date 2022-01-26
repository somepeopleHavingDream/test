package org.yangxin.test.proxy.proxy2;

/**
 * 用户管理接口实现类
 *
 * @author yangxin
 * 2022/1/26 17:48
 */
public class UserManagerImpl implements UserManager {

    @Override
    public void addUser(String username, String password) {
        System.out.println("调用了新增的方法");
        System.out.println("username: " + username + ", password: " + password);
    }

    @Override
    public void deleteUser(String username) {
        System.out.println("调用了删除的方法");
        System.out.println("username: " + username);
    }
}
