package com.jincong.springboot.test;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author j_cong
 * @version V1.0
 * @date 2022/1/20
 */
@Slf4j
public class CompletableFutureTest {

    private static final ExecutorService executorServe = new ThreadPoolExecutor(10,
            Runtime.getRuntime().availableProcessors() * 2,
            5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000),
            new ThreadFactoryBuilder().setNameFormat("Async-Pool-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {


        // supplyAsync();

        // combine();


        //获取用户信息详情
        allOf();

    }


    private static String supplyAsync() throws ExecutionException, InterruptedException {
        log.info("开始执行任务");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("3秒后任务执行结束");

            return "任务结束";
        });

        log.info(completableFuture.get());
        return completableFuture.get();
    }


    private static void combine() throws ExecutionException, InterruptedException {

        log.info("combine start");

        CompletableFuture<Double> weightFuture = CompletableFuture.supplyAsync(() -> {

            log.info("weightFuture start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("weightFuture end");
            return 65.0;
        });
        CompletableFuture<Double> heightFuture = CompletableFuture.supplyAsync(() -> {
            log.info("heightFuture start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("heightFuture end");
            return 183.8;
        });


        CompletableFuture<Void> all = CompletableFuture.allOf(weightFuture, heightFuture);

        all.get();

        log.info("combine end");

    }


    /**
     * 并发异步处理任务，汇聚结果
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    private static void allOf() throws ExecutionException, InterruptedException, TimeoutException {

        /**
         * 自定义线程池
         */
        ExecutorService executorServe = new ThreadPoolExecutor(10,
                Runtime.getRuntime().availableProcessors() * 2,
                5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000),
                new ThreadFactoryBuilder().setNameFormat("Async-Pool-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());

        log.info("allOf start");
        List<String> result = Lists.newArrayList();

        Instant start = Instant.now();

        // allOf表示等待所有的任务结束，真正处理时间取决于最慢的一个任务
        CompletableFuture.allOf(
                        CompletableFuture.supplyAsync(CompletableFutureTest::getData, executorServe)
                                .whenComplete((v, err) -> result.add(Thread.currentThread() + ":::" + v + "A"))
                                .exceptionally(e -> null),
                        CompletableFuture.supplyAsync(CompletableFutureTest::getData, executorServe)
                                .whenComplete((v, err) -> result.add(Thread.currentThread() + ":::" + v + "B"))
                                .exceptionally(e -> null),
                        CompletableFuture.supplyAsync(CompletableFutureTest::getData, executorServe)
                                .whenComplete((v, err) -> result.add(Thread.currentThread() + ":::" + v + "C"))
                                .exceptionally(e -> null))
                .whenComplete((key, value) -> {
                    log.info("all task finished !");
                    result.forEach(System.out::println);
                });


        log.info("allOf end, const time: {}", (ChronoUnit.SECONDS.between(start, Instant.now())));
        System.out.println(result);
        executorServe.shutdown();

    }

    private static String getData() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int anInt = random.nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(anInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return anInt + "";
    }


}
