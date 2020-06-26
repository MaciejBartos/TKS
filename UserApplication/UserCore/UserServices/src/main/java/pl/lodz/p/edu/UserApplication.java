package pl.lodz.p.edu;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public Queue createUserUserService(){
        return new Queue("createUserUserService", false);
    }

    @Bean
    public Queue createUserRentService(){
        return new Queue("createUserRentService", false);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("createUserExchange");
    }

    @Bean
    Binding bindingUserService(Queue createUserUserService, FanoutExchange exchange) {
        return BindingBuilder.bind(createUserUserService).to(exchange);
    }

    @Bean
    Binding bindingRentService(Queue createUserRentService, FanoutExchange exchange) {
        return BindingBuilder.bind(createUserRentService).to(exchange);
    }

    @Bean
    public Queue editUserUserService(){
        return new Queue("editUserUserService", false);
    }

    @Bean
    public Queue editUserRentService(){
        return new Queue("editUserRentService", false);
    }

    @Bean
    FanoutExchange exchangeEdit() {
        return new FanoutExchange("editUserExchange");
    }

    @Bean
    Binding bindingUserServiceEdit(Queue editUserUserService, FanoutExchange exchangeEdit) {
        return BindingBuilder.bind(editUserUserService).to(exchangeEdit);
    }

    @Bean
    Binding bindingRentServiceEdit(Queue editUserRentService, FanoutExchange exchangeEdit) {
        return BindingBuilder.bind(editUserRentService).to(exchangeEdit);
    }

    @Bean
    public Queue changeStateUserService(){
        return new Queue("changeStateUserService", false);
    }

    @Bean
    public Queue changeStateRentService(){
        return new Queue("changeStateRentService", false);
    }

    @Bean
    FanoutExchange exchangeState() {
        return new FanoutExchange("changeStateExchange");
    }

    @Bean
    Binding bindingUserServiceState(Queue changeStateUserService, FanoutExchange exchangeState) {
        return BindingBuilder.bind(changeStateUserService).to(exchangeState);
    }

    @Bean
    Binding bindingRentServiceState(Queue changeStateRentService, FanoutExchange exchangeState) {
        return BindingBuilder.bind(changeStateRentService).to(exchangeState);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate rabbitTemplate1(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue deleteUserRentService(){
        return new Queue("deleteUserRentService", false);
    }

    @Bean
    FanoutExchange exchangeDelete() {
        return new FanoutExchange("deleteUserExchange");
    }
    
    @Bean
    Binding bindingRentServiceDelete(Queue deleteUserRentService, FanoutExchange exchangeEdit) {
        return BindingBuilder.bind(deleteUserRentService).to(exchangeEdit);
    }
    
}
