package wyj.spring.rabbit.demo4;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Tut4Config {

	@Bean
	public DirectExchange direct() {
		return new DirectExchange("tut.direct");
	}

	private static class ReceiverConfig {

		@Bean
		public Queue autoDeleteQueue1() {
			return new AnonymousQueue();
		}

		@Bean
		public Queue autoDeleteQueue2() {
			return new AnonymousQueue();
		}

		@Bean
		public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("odd");
		}

		@Bean
		public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("even");
		}

		@Bean
		public Tut4Receiver receiver() {
			return new Tut4Receiver();
		}

	}

	@Bean
	public Tut4Sender sender() {
		return new Tut4Sender();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Tut4Config.class, args);
	}
}
