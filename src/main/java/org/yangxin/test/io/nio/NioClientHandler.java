package org.yangxin.test.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端线程类，专门接收服务器端响应信息
 *
 * @author yangxin
 * 2020/05/25 14:59
 */
public class NioClientHandler implements Runnable {

    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int readyChannels = selector.select();

                if (readyChannels == 0) {
                    continue;
                }

                // 获取可用channel的集合
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    // selectionKey实例
                    SelectionKey selectionKey = iterator.next();

                    // 移除set中的当前selectionKey
                    iterator.remove();

                    // 根据就绪状态，调用对应方法处理业务逻辑
                    // 如果是可读事件
                    if (selectionKey.isReadable()) {
                        readHandler(selectionKey, selector);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 可读事件处理器
     */
    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
        // 要从selectionKey中获取到已经就绪的channel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        // 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 循环读取服务器端响应信息
        StringBuilder response = new StringBuilder();
        while (socketChannel.read(byteBuffer) > 0) {
            // 切换buffer为读模式
            byteBuffer.flip();

            // 读取buffer中的内容
            response.append(StandardCharsets.UTF_8.decode(byteBuffer));
        }

        // 将channel再次注册到selector上，监听它的可读事件
        socketChannel.register(selector, SelectionKey.OP_READ);

        // 将服务器端响应信息打印到本地
        if (response.length() > 0) {
            System.out.println(response);
        }
    }
}
