package org.yangxin.test.rpc.grpc.loadbalance;

import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import org.yangxin.test.rpc.grpc.HelloWorldConstant;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;

public class LoadBalanceNameResolverProvider extends NameResolverProvider {

    private final List<InetSocketAddress> socketAddressList;

    public LoadBalanceNameResolverProvider(List<InetSocketAddress> socketAddressList) {
        this.socketAddressList = socketAddressList;
    }

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 5;
    }

    @Override
    public NameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        return new LoadBalanceNameResolver(socketAddressList, HelloWorldConstant.SERVICE_NAME);
    }

    @Override
    public String getDefaultScheme() {
        return HelloWorldConstant.SCHEME;
    }
}
