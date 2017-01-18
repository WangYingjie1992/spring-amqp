package wyj.spring.rabbit.demo2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class Tut2Sender implements CommandLineRunner {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	//@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		for (int i = 0; i < 100; i++)
		{
			String message = "Hello wangyingjie!  " + i;
			//channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes("UTF-8"));
			this.template.convertAndSend(queue.getName(), message);
		}
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		this.send();
	}

}