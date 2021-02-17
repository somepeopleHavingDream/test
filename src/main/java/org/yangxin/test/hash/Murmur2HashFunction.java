package org.yangxin.test.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leifu
 * @Date 2017年4月16日
 * @Time 下午10:35:17
 */
@SuppressWarnings({"JavaDoc", "AlibabaUndefineMagicConstant", "DuplicatedCode", "AlibabaSwitchStatement"})
public class Murmur2HashFunction extends HashFunction{

    private static final long serialVersionUID = -3640249373409996794L;

    @Override
    public List<Integer> hash(byte[] value, int em, int ka) {
        List<Integer> positionList = new ArrayList<>(ka);

        int hashes = 0;
        int lastHash;
        byte[] data = value.clone();
        while (hashes < ka) {


            for (int i = 0; i < value.length; i++) {
                if (data[i] == 127) {
                    data[i] = 0;
                } else {
                    data[i]++;
                    break;
                }
            }

            // 'size' and 'r' are mixing constants generated offline.
            // They're not really 'magic', they just happen to work well.
            int m = 0x5bd1e995;
            int r = 24;

            // Initialize the hash to a 'random' value
            int len = data.length;
            int h = SEED_32 ^ len;

            int i = 0;
            while (len >= 4) {
                int k = data[i] & 0xFF;
                k |= (data[i + 1] & 0xFF) << 8;
                k |= (data[i + 2] & 0xFF) << 16;
                k |= (data[i + 3] & 0xFF) << 24;

                k *= m;
                k ^= k >>> r;
                k *= m;

                h *= m;
                h ^= k;

                i += 4;
                len -= 4;
            }

            switch (len) {
                case 3:
                    h ^= (data[i + 2] & 0xFF) << 16;
                case 2:
                    h ^= (data[i + 1] & 0xFF) << 8;
                case 1:
                    h ^= (data[i] & 0xFF);
                    h *= m;
            }

            h ^= h >>> 13;
            h *= m;
            h ^= h >>> 15;

            lastHash = rejectionSample(h, em);
            if (lastHash != -1) {
                positionList.add(lastHash);
                hashes++;
            }
        }
        return positionList;
    }

}
