package wyj.spring.rabbit.demo3;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class Tut3Sender implements CommandLineRunner {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private FanoutExchange fanout;

	public void send() {
		for (int i = 0; i < 100; i++)
		{
			String message = "from wangyingjie  " + i;
			this.template.convertAndSend(fanout.getName(), "", message);
		}
	}

	@Override
	public void run(String... arg0) throws Exception {
		this.send();
	}

}