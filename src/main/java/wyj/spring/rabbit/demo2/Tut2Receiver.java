package wyj.spring.rabbit.demo2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "tut.hello")
public class Tut2Receiver {

	private final int instance;

	public Tut2Receiver(int i) {
		this.instance = i;
	}

	@RabbitHandler
	public void receive(String in) throws InterruptedException {
		System.out.println("instance " + this.instance + " [x] Received '" + in + "'");
		doWork(in);
	}

	private void doWork(String in) throws InterruptedException {
		Thread.sleep(100);
	}
}
