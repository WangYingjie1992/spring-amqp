package wyj.spring.rabbit.demo5;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class Tut5Sender implements CommandLineRunner {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private TopicExchange topic;

	public void send() {

		for (int i = 1; i < 100; i++)
		{

			if (i % 2 == 0)
			{
				if (i % 10 == 0)
				{
					//发布给ruting_key为“*.even.tenTimes”的消费者
					String even_tenTimes = "even|tenTimes " + i;
					this.template.convertAndSend(topic.getName(), "n.even.tenTimes", even_tenTimes);
				}
				//发布给ruting_key为“”的消费者
				String even = "even  " + i;
				this.template.convertAndSend(topic.getName(), "n.even.n", even);
			} else
			{

				//发布给ruting_key为“odd”的消费者
				String odd = "odd  " + i;
				this.template.convertAndSend(topic.getName(), "odd.n.n", odd);
			}
		}
	}

	@Override
	public void run(String... arg0) throws Exception {
		this.send();
	}

}