package org.yangxin.test.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * @author leifu
 * @Date 2017年4月16日
 * @Time 下午9:56:30
 */
@SuppressWarnings("JavaDoc")
public class Crc32HashFunction extends HashFunction {

    private static final long serialVersionUID = 3175307974705695650L;

    @Override
    public List<Integer> hash(byte[] value, int m, int k) {
        Checksum cs = new CRC32();
        List<Integer> positionList = new ArrayList<>(k);
        int hashes = 0;
        int salt = 0;
        while (hashes < k) {
            cs.reset();
            cs.update(value, 0, value.length);
            // Modify the data to be checksummed by adding the number of already
            // calculated hashes, the loop counter and
            // a static seed
            cs.update(hashes + salt++ + SEED_32);
            int hash = rejectionSample((int) cs.getValue(), m);
            if (hash != -1) {
                positionList.add(hash);
                hashes++;
            }
        }
        return positionList;
    }

    public static int calculateCRC16(String key) {
        CRC32 crc32 = new CRC32();
        crc32.update(key.getBytes());
        return (int) crc32.getValue() & 0xFFFF; // Ensure it's a 16-bit unsigned value
    }

    public static void main(String[] args) {
        String yourKey = "fancy_batch_gift_record";
        int hashValue = calculateCRC16(yourKey);
        System.out.println("CRC16 Hash Value: " + hashValue);

        int hashSlot = hashValue % 16384;
        System.out.println(hashSlot);
    }
}
