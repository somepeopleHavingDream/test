package org.yangxin.test.ddd;

import lombok.NonNull;
import org.apache.commons.lang.StringUtils;

import javax.validation.ValidationException;
import java.util.Arrays;

/**
 * @author yangxin
 * 2021/6/18 11:44
 */
public class RegistrationServiceImpl implements IRegistrationService {

    private SalesRepRepository salesRepRepo;
    private UserRepository userRepo;

    public User register(@NonNull Name name, @NonNull PhoneNumber phone, @NonNull Address address)
            throws ValidationException {
        // 找到区域内的SalesRep
        SalesRep rep = salesRepRepo.findRep(phone.getAreaCode());

        // 最后创建用户，落盘，然后返回，这部分代码实际上也能用Builder来解决
        User user = User.builder()
                .name(name)
                .phone(phone)
                .address(address)
                .build();
        if (rep != null) {
            user.setRepId(rep.getRepId());
        }

        return userRepo.saveUser(user);
    }

    public static void main(String[] args) {
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        User register = registrationService.register(null, null, null);

        System.out.println(register);
    }
}
