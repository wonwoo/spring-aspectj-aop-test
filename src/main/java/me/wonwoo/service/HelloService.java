package me.wonwoo.service;

import me.wonwoo.repository.Hello;
import me.wonwoo.repository.HelloRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wonwoo on 2017. 3. 20..
 */
@Component
public class HelloService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private final HelloRepository helloRepository;

  public HelloService(HelloRepository helloRepository) {
    this.helloRepository = helloRepository;
  }

  public void hello() {
    this.rollbackHello();
  }

  public void notHello() {
    this.notRollbackHello();
  }

  private void notRollbackHello() {
    helloRepository.save(new Hello("wonwoo"));
    if (true) {
      throw new RuntimeException();
    }
    helloRepository.save(new Hello("kevin"));
  }

  @Transactional
  private void rollbackHello() {
    helloRepository.save(new Hello("wonwoo"));
    if (true) {
      throw new RuntimeException();
    }
    helloRepository.save(new Hello("kevin"));
  }

  @Async
  public void world() {
    logger.info("hello world");
  }
}
