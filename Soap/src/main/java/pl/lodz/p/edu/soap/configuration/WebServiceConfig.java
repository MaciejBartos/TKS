//package pl.lodz.p.edu.soap.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ws.config.annotation.EnableWs;
////import pl.lodz.p.edu.soap.services.UserSoapService;
//
//import javax.annotation.PostConstruct;
//import javax.xml.ws.Endpoint;
//
//@Configuration
//@EnableWs
//public class WebServiceConfig {
//
//    @Autowired
//    private ApplicationContext context;
//
//    @PostConstruct
//    public void init() {
//        Endpoint.publish("http://localhost:5555/userSoapService", context.getBean(UserSoapService.class));
//    }
//}