package me.wonwoo;

import me.wonwoo.repository.HelloRepository;
import me.wonwoo.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAspectjAopTestApplicationTests {

	@Autowired
	private HelloService helloService;

	@Autowired
	private HelloRepository helloRepository;

	@Test
	public void tester() {

		helloService.world();
		try {
			helloService.hello();
		} catch (Exception ignored) {
		}
		assertThat(helloRepository.findAll()).hasSize(0);
		helloRepository.deleteAll();
		try {
			helloService.notHello();
		} catch (Exception ignored) {
		}
		assertThat(helloRepository.findAll()).hasSize(1);
	}

}
