package wyj.spring_amqp;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Send {

	public static void main(final String... args) throws Exception {

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");

		// set up the queue, exchange, binding on the broker
		RabbitAdmin admin = new RabbitAdmin(connectionFactory);
		Queue queue = new Queue("myQueue");
		admin.declareQueue(queue);
		TopicExchange exchange = new TopicExchange("myExchange");
		admin.declareExchange(exchange);
		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(""));

		// send something
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		for (int i = 0; i < 100; i++)
		{
			template.convertAndSend("myExchange", "foo.bar", "Hello, world!" + i);
			Thread.sleep(1000);

		}

	}

}
