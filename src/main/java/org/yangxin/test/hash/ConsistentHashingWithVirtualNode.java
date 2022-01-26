package org.yangxin.test.hash;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 带虚拟节点的一致性Hash算法
 * 重点：1 如何造一个Hash环 2 如何在哈希环上映射服务器节点 3 如何找到对应的节点
 *
 * @author yangxin
 * 2022/1/25 18:33
 */
public class ConsistentHashingWithVirtualNode {

    /**
     * 待添加入Hash环的服务器列表
     */
    private static final String[] SERVERS = {"192.168.0.0:111",
            "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};

    /**
     * 真实节点列表，考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList
     */
    private static final List<String> REAL_NODES = new LinkedList<>();

    /**
     * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
     */
    private static final SortedMap<Integer, String> VIRTUAL_NODES = new TreeMap<>();

    /**
     * 虚拟节点的数目，这里写死，为了演示需要，一个真实的节点对应5个虚拟节点
     */
    private static final Integer VIRTUAL_NODES_NUM = 5;

    static {
        // 先把原始的服务器添加到真实结点列表中
        REAL_NODES.addAll(Arrays.asList(SERVERS));

        // 再添加虚拟节点，遍历LinkedList，使用foreach循环效率会比较高
        for (String realNode : REAL_NODES) {
            for (int i = 0; i < VIRTUAL_NODES_NUM; i++) {
                String virtualNodeName = realNode + "&&VN" + i;
                int hash = getHash(virtualNodeName);

                System.out.println("虚拟节点[" + virtualNodeName + "]被添加，hash值为" + hash);
                VIRTUAL_NODES.put(hash, virtualNodeName);
            }
        }
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值，这里不使用重写hashCode的方法，最终效果没区别
     *
     * @param str ip
     * @return 哈希值
     */
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }

        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果计算出来的值为负数，则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }

        return hash;
    }

    /**
     * 得到应当路由到的结点
     *
     * @param key 键
     * @return 应当路由到的结点
     */
    private static String getServer(String key) {
        // 得到该key的hash值
        int hash = getHash(key);

        // 得到大于该hash值的所有map
        SortedMap<Integer, String> subMap = VIRTUAL_NODES.tailMap(hash);
        String virtualNode;
        if (subMap.isEmpty()) {
            // 如果没有比该key的hash值大的，则从第一个node开始
            int firstKey = VIRTUAL_NODES.firstKey();
            // 返回对应的服务器
            virtualNode = VIRTUAL_NODES.get(firstKey);
        } else {
            // 第一个key就是顺时针过去离node最近的那个结点
            int firstKey = subMap.firstKey();
            // 返回对应的服务器
            virtualNode = subMap.get(firstKey);
        }

        // virtualNode虚拟节点名称要截取一下
        return StringUtils.isNotBlank(virtualNode) ? virtualNode.substring(0, virtualNode.indexOf("&&")) : null;
    }

    public static void main(String[] args) {
        String[] keys = {"太阳", "月亮", "星星"};
        for (String key : keys) {
            System.out.println(key + "的Hash值为" + getHash(key) + "，被路由到结点[" + getServer(key) + "]");
        }
    }
}
