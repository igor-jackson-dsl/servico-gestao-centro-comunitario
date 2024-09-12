package dsl.jackson.igor.SGCC.model.service.Implementation;

import dsl.jackson.igor.SGCC.model.service.NotificacaoService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    private final RabbitTemplate rabbitTemplate;

    private final Queue notificationQueue;

    public NotificacaoServiceImpl(RabbitTemplate rabbitTemplate, Queue notificationQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.notificationQueue = notificationQueue;
    }

    public void enviarNotificacao(String mensagem) {
        rabbitTemplate.convertAndSend(notificationQueue.getName(), mensagem);
    }
}