package dsl.jackson.igor.SGCC.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public Queue notificationQueue() {
        return new Queue("notificationFullQueue", false);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory,
//                                                                   MessageListenerAdapter messageListenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames("notificationFullQueue");
//        container.setMessageListener(messageListenerAdapter);
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter messageListenerAdapter() {
//        return new MessageListenerAdapter(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                System.out.println("Received <" + new String(message.getBody()) + ">");
//            }
//        });
//    }
}