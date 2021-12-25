package org.yangxin.test.netty.demo;

/**
 * @author yangxin
 * 2021/12/24 15:44
 */
@SuppressWarnings("SameParameterValue")
public class PooledByteBufAllocatorTest {

    public static void main(String[] args) {
        System.out.println(validateAndCalculateChunkSize(8192, 11));
    }

    private static int validateAndCalculateChunkSize(int pageSize, int maxOrder) {
        int chunkSize = pageSize;
        for (int i = maxOrder; i > 0; i--) {
            chunkSize <<= 1;
        }

        return chunkSize;
    }
}
