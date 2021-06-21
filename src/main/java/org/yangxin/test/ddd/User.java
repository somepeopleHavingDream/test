package org.yangxin.test.ddd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangxin
 * 2021/6/18 11:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long userId;

    private Name name;

    private PhoneNumber phone;

    private Address address;

    private RepId repId;
}
