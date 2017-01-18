package wyj.spring.rabbit.demo1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "tut.hello")
public class Tut1Receiver {

	@RabbitHandler
	public void receive(String in) {
		System.out.println(" [x] Received '" + in + "'");
	}

}
