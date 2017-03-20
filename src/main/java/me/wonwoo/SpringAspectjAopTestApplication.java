package me.wonwoo;

import java.util.concurrent.Executor;
import me.wonwoo.repository.HelloRepository;
import me.wonwoo.service.HelloService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableAsync(mode = AdviceMode.ASPECTJ)
public class SpringAspectjAopTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringAspectjAopTestApplication.class, args);
  }

  @Bean(name = "threadPoolTaskExecutor")
  public Executor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
    threadPoolTaskExecutor.setMaxPoolSize(30);
    threadPoolTaskExecutor.setQueueCapacity(50);
    threadPoolTaskExecutor.setThreadNamePrefix("test-async");
    threadPoolTaskExecutor.initialize();
    return threadPoolTaskExecutor;
  }

  @Bean
  CommandLineRunner commandLineRunner(HelloService helloService, HelloRepository helloRepository) {
    return args -> {
      helloService.world();
      System.out.println("test");
      try {
        helloService.hello();
      } catch (Exception ignored) {
      }
      helloRepository.findAll().forEach(System.out::println);
      helloRepository.deleteAll();
      try {
        helloService.notHello();
      } catch (Exception ignored) {
      }
      helloRepository.findAll().forEach(System.out::println);
      helloRepository.deleteAll();
    };
  }
}
