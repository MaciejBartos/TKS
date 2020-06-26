package pl.lodz.p.edu;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.edu.controller.UserController;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@SpringBootApplication
public class PasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasApplication.class, args);
	}


	@Bean
	public ServletWebServerFactory servletContainer() {
		// Enable SSL Trafic
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		// Add HTTP to HTTPS redirect
		tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

		return tomcat;
	}

	private Connector httpToHttpsRedirectConnector() {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean(name = "restTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder builder) throws NoSuchAlgorithmException, KeyManagementException {

		TrustManager[] trustAllCerts = new TrustManager[] {
				new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[0];
					}
					public void checkClientTrusted(
							X509Certificate[] certs, String authType) {
					}
					public void checkServerTrusted(
							X509Certificate[] certs, String authType) {
					}
				}
		};
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLContext(sslContext)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
				.build();
		HttpComponentsClientHttpRequestFactory customRequestFactory = new HttpComponentsClientHttpRequestFactory();
		customRequestFactory.setHttpClient(httpClient);
		return builder.requestFactory(() -> customRequestFactory).build();
	}



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
}
