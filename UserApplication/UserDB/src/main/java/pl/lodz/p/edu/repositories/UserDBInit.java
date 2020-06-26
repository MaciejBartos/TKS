package pl.lodz.p.edu.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import pl.lodz.p.edu.data.UsersEnt.AdminEnt;
import pl.lodz.p.edu.data.UsersEnt.ClientEnt;
import pl.lodz.p.edu.data.UsersEnt.ResourcesManagerEnt;
import pl.lodz.p.edu.data.UsersEnt.UserEnt;

import java.util.UUID;

public class UserDBInit implements CommandLineRunner {

    private final IUserRepoEnt<UserEnt> userRepo;
    //private final PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();

    @Autowired
    public UserDBInit(IUserRepoEnt<UserEnt> userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public void run(String... args) throws Exception{

       // userRepo.add(new AdminEnt("Franek", UUID.randomUUID(), true,"admin", passwordEncoder.encode("admin")));
       // userRepo.add(new ClientEnt("Kamil", UUID.randomUUID(), true,"client", passwordEncoder.encode("client")));
        //userRepo.add(new ClientEnt("Kamil", UUID.randomUUID(), true,"client1", passwordEncoder.encode("client")));
       // userRepo.add(new ResourcesManagerEnt("Maciek", UUID.randomUUID(), true,"manager",passwordEncoder.encode("manager")));

    }
}
