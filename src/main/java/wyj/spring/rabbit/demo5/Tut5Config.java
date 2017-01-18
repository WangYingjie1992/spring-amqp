package wyj.spring.rabbit.demo5;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Tut5Config {

	@Bean
	public TopicExchange topic() {
		return new TopicExchange("tut.topic");
	}


		@Bean
		public Queue autoDeleteQueue1() {
			return new AnonymousQueue();
		}

		@Bean
		public Queue autoDeleteQueue2() {
			return new AnonymousQueue();
		}

		@Bean
		public Binding binding1a(TopicExchange topic, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("odd.*.*");
		}

		@Bean
		public Binding binding2a(TopicExchange topic, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("*.even.*");
		}

		@Bean
		public Binding binding2b(TopicExchange topic, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("*.*.tenTimes");
		}

		@Bean
		public Tut5Receiver receiver() {
			return new Tut5Receiver();
		}


	@Bean
	public Tut5Sender sender() {
		return new Tut5Sender();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Tut5Config.class, args);
	}
}
