package wyj.spring_amqp.copy;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

public class RecEven {
	public static void main(String[] args) {
		ConnectionFactory cf = new CachingConnectionFactory("localhost");

		// set up the queue, exchange, binding on the broker
		RabbitAdmin admin = new RabbitAdmin(cf);
		Queue queue = new Queue("myQueue");
		admin.declareQueue(queue);
		TopicExchange exchange = new TopicExchange("myExchange");
		admin.declareExchange(exchange);
		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("*.even.*"));

		// set up the listener and container
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);
		Object listener = new Object() {
			public void handleMessage(String foo) throws InterruptedException {
				Thread.sleep(1000);
				System.out.println(foo);

			}
		};
		MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
		container.setMessageListener(adapter);
		container.setQueueNames("myQueue");
		container.start();
		//container.stop();
	}

}
