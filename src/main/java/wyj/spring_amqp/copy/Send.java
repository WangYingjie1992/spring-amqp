package wyj.spring_amqp.copy;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Send {

	public static void main(final String... args) throws Exception {

		ConnectionFactory cf = new CachingConnectionFactory("localhost");

		// set up the queue, exchange, binding on the broker
		RabbitAdmin admin = new RabbitAdmin(cf);
		Queue queue = new Queue("myQueue");
		admin.declareQueue(queue);
		TopicExchange exchange = new TopicExchange("myExchange");
		admin.declareExchange(exchange);
		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(""));

		// send something
		RabbitTemplate template = new RabbitTemplate(cf);
		for (int i = 1; i < 100; i++)
		{

			if (i % 2 == 0)
			{
				if (i % 10 == 0)
				{
					//发布给ruting_key为“*.even.tenTimes”的消费者
					template.convertAndSend("myExchange", "n.even.tenTimes", "even&tenTimes" + i);
				} else
				{
					//发布给ruting_key为“”的消费者
					template.convertAndSend("myExchange", "n.even.n", "even" + i);
				}
			} else
			{
				//发布给ruting_key为“odd”的消费者
				template.convertAndSend("myExchange", "odd.n.n", "odd" + i);
			}

		}
		System.out.println("发送完成！");

	}

}
