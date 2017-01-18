package wyj.spring.rabbit.demo1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class Tut1Sender implements CommandLineRunner {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	//@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		String message = "Hello World!";
		this.template.convertAndSend(queue.getName(), message);
		System.out.println(" [x] Sent '" + message + "'");
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		this.send();
	}

}