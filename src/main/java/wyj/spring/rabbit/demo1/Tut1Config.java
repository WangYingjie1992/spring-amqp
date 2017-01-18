package wyj.spring.rabbit.demo1;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Tut1Config {

	@Bean
	public Queue hello() {
		return new Queue("tut.hello");
	}

	@Bean
	public Tut1Receiver receiver() {
		return new Tut1Receiver();
	}

	@Bean
	public Tut1Sender sender() {
		return new Tut1Sender();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Tut1Config.class, args);
	}
}
