package org.yangxin.test.hash;

import java.io.Serializable;
import java.util.List;

/**
 * hash函数接口类
 * @author leifu
 * @Date 2017年4月16日
 * @Time 下午5:25:42
 */
@SuppressWarnings({"JavaDoc", "AlibabaUndefineMagicConstant"})
public abstract class HashFunction implements Serializable {
    
    private static final long serialVersionUID = -1074935532939858765L;
    
    protected static final int SEED_32 = 89478583;
    
    /**
     * Performs rejection sampling on a random 32bit Java int (sampled from Integer.MIN_VALUE to Integer.MAX_VALUE).
     *
     * @param random int
     * @param m     integer output range [1,size]
     * @return the number down-sampled to interval [0, size]. Or -1 if it has to be rejected.
     */
    protected int rejectionSample(int random, int m) {
        random = Math.abs(random);
        if (random > (2147483647 - 2147483647 % m)
                || random == Integer.MIN_VALUE) {
            return -1;
        } else {
            return random % m;
        }
    }

    /**
     * Computes hash values.
     *
     * @param value the byte[] representation of the element to be hashed
     * @param m integer output range [1,size]
     * @param k number of hashes to be computed
     * @return hash values
     */
    public abstract List<Integer> hash(byte[] value, int m, int k);
}