package org.yangxin.test.disruptor.ability;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yangxin
 * 2022/1/2 17:04
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data implements Serializable {

    private static final long serialVersionUID = -2133404256055143312L;

    private Long id;

    private String name;
}
