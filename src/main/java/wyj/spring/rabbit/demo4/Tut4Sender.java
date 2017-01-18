package wyj.spring.rabbit.demo4;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class Tut4Sender implements CommandLineRunner {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private DirectExchange direct;

	public void send() {

		for (int i = 1; i < 100; i++)
		{

			if (i % 2 == 0)
			{
				String message = "even  " + i;
				this.template.convertAndSend(direct.getName(), "even", message);
			} else
			{
				String message = "odd  " + i;
				this.template.convertAndSend(direct.getName(), "odd", message);
			}
		}
	}

	@Override
	public void run(String... arg0) throws Exception {
		this.send();
	}

}