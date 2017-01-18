package wyj.spring.rabbit.demo3;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "tut.hello")
public class Tut3Receiver {

	@RabbitListener(queues = "#{autoDeleteQueue1.name}")
	public void receive1(String in) throws InterruptedException {
		receive(in, 1);
	}

	@RabbitListener(queues = "#{autoDeleteQueue2.name}")
	public void receive2(String in) throws InterruptedException {
		receive(in, 2);
	}

	@RabbitHandler
	public void receive(String in, int receiver) throws InterruptedException {
		System.out.println("instance " + receiver + " [x] Received '" + in + "'");
		doWork(in);
	}

	private void doWork(String in) throws InterruptedException {
		Thread.sleep(100);
	}
}
