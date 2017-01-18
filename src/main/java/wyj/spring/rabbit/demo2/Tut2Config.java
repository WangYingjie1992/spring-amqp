package wyj.spring.rabbit.demo2;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Tut2Config {

	@Bean
	public Queue hello() {
		return new Queue("tut.hello");
	}

	private static class ReceiverConfig {

		@Bean
		public Tut2Receiver receiver1() {
			return new Tut2Receiver(1);
		}

		@Bean
		public Tut2Receiver receiver2() {
			return new Tut2Receiver(2);
		}

	}

	@Bean
	public Tut2Sender sender() {
		return new Tut2Sender();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Tut2Config.class, args);
	}
}
