# spring-amqp
[Spring AMQP](http://projects.spring.io/spring-amqp/)

 project applies core Spring concepts to the development of AMQP-based messaging solutions. It provides a "template" as a high-level abstraction for sending and receiving messages. It also provides support for Message-driven POJOs with a "listener container". These libraries facilitate management of AMQP resources while promoting the use of dependency injection and declarative configuration. In all of these cases, you will see similarities to the JMS support in the Spring Framework.



[RabbitMQ Tutorials](http://www.rabbitmq.com/getstarted.html)

**spring-amqp**的官方文档按照RabbitMQ官网上的6个demo用spring-amqp的方式去实现。但是里面用到了spring @Profile的知识，对于缺乏这块知识的同学看起来可能需要花费一些时间，我这个工程也是按照RabbitMQ官网上的6个dome所讲知识内容，区别是去掉了@profile的内容以及换用了一个相对来说更加容易理解的例子演示RabbitMQ的几种使用方式。