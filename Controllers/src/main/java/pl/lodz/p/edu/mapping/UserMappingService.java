package pl.lodz.p.edu.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.edu.model.UsersGUI.UserGUI;

@Service
public class UserMappingService implements UserDetailsService {

//    private final ShowUserByNameUseCase userRepo;
    private RestTemplate restTemplate;

    @Autowired
    public UserMappingService( RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
//        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userRepo.get(s);
        UserGUI user = restTemplate.getForObject("http://localhost:8444/api/v1/users/name/" + s, UserGUI.class);
        UserMapping userMapping = null;
        if(user != null){
            userMapping = new UserMapping(user);
        }

        return userMapping;
    }
}
