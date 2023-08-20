package org.yangxin.test.spring.resolver;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author yangxin
 * 2021/11/21 下午9:15
 */
public class ResourcePatternResolverTest {

    public static void main(String[] args) throws IOException {
        test1();
    }

    private static void test1() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:/*.yml");
//        Resource[] resources = resolver.getResources("classpath:/*.yml");
        for (Resource resource : resources) {
            // 文件名
            System.out.println(resource.getFilename());
            // 文件绝对路径
            System.out.println(resource.getURL().getPath());
            // File对象
            System.out.println(resource.getFile());
            // InputStream对象
            System.out.println(resource.getInputStream());
        }
    }
}
