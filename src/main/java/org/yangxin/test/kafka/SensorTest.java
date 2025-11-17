package org.yangxin.test.kafka;

import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.metrics.Metrics;
import org.apache.kafka.common.metrics.Sensor;
import org.apache.kafka.common.metrics.stats.*;

import java.util.concurrent.TimeUnit;

public class SensorTest {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1() throws InterruptedException {
        // 创建指标系统
        Metrics metrics = new Metrics();
        Sensor sensor = metrics.sensor("demo-sensor");

        // 1. 计数（Count）
        MetricName countMetricName = metrics.metricName("total-count", "test");
        sensor.add(countMetricName, new Count());

        // 2. 速率（Rate）
        MetricName rateMetricName = metrics.metricName("events-per-second", "test");
        sensor.add(rateMetricName, new Rate(TimeUnit.SECONDS));

        // 3. 平均值（Avg）
        MetricName avgMetricName = metrics.metricName("average-value", "test");
        sensor.add(avgMetricName, new Avg());

        // 4. 最大值（Max）
        MetricName maxMetricName = metrics.metricName("max-value", "test");
        sensor.add(maxMetricName, new Max());

        // 5. 最小值（Min）
        MetricName minMetricName = metrics.metricName("min-value", "test");
        sensor.add(minMetricName, new Min());

        // 6. 百分比（Percentiles）
        Percentile p95 = new Percentile(metrics.metricName("p95", "test"), 95.0);
        Percentile p99 = new Percentile(metrics.metricName("p99", "test"), 99.0);
        sensor.add(new Percentiles(100, -1000, 1000, Percentiles.BucketSizing.CONSTANT, p95, p99));

        System.out.println("=== 开始记录数据 ===");

        // 记录数据
        for (int i = 1; i <= 100; i++) {
            sensor.record(i); // 记录1到100
            Thread.sleep(10); // 模拟时间间隔，让速率计算有意义
        }

        // 等待一下，让指标计算完成
        Thread.sleep(1000);

        System.out.println("\n=== 指标计算结果 ===");

        // 获取并打印所有指标值
        printMetricValue(metrics, countMetricName, "总计数");
        printMetricValue(metrics, rateMetricName, "事件速率(个/秒)");
        printMetricValue(metrics, avgMetricName, "平均值");
        printMetricValue(metrics, maxMetricName, "最大值");
        printMetricValue(metrics, minMetricName, "最小值");
        printMetricValue(metrics, metrics.metricName("p95", "test"), "95分位值");
        printMetricValue(metrics, metrics.metricName("p99", "test"), "99分位值");

        // 关闭指标系统
        metrics.close();
    }

    private static void printMetricValue(Metrics metrics, MetricName metricName, String description) {
        Metric metric = metrics.metric(metricName);
        if (metric != null) {
            double value = (double) metric.metricValue();
            System.out.printf("%-20s: %.2f%n", description, value);
        } else {
            System.out.printf("%-20s: 指标不存在%n", description);
        }
    }
}
