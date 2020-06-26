package pl.lodz.p.edu.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.data.FirmsEnt.InterCityEnt;
import pl.lodz.p.edu.data.FirmsEnt.RegioEnt;
import pl.lodz.p.edu.data.FirmsEnt.TLKEnt;
import pl.lodz.p.edu.data.TrainsEnt.ExpressTrainEnt;
import pl.lodz.p.edu.data.TrainsEnt.PassengerTrainEnt;
import pl.lodz.p.edu.data.UsersEnt.UserClientEnt;
import pl.lodz.p.edu.model.Clients.UserClient;


import java.util.UUID;

@Service
public class DBInit implements CommandLineRunner {

    private final UserClientRepoEnt userRepo;
//    private final PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
    private final TrainRepoEnt trainRepo;
    private final TicketRepoEnt ticketRepo;

    @Autowired
    public DBInit(UserClientRepoEnt userRepo, TrainRepoEnt trainRepo, TicketRepoEnt ticketRepo) {
        this.userRepo = userRepo;
        this.ticketRepo = ticketRepo;
        this.trainRepo = trainRepo;
    }

    @Override
    public void run(String... args) {

//        userRepo.add(new UserClientEnt("Franek","Admin", UUID.randomUUID(), true,"admin", passwordEncoder.encode("admin")));
//        userRepo.add(new UserClientEnt("Kamil","Client", UUID.randomUUID(), true,"client", passwordEncoder.encode("client")));
//        userRepo.add(new UserClientEnt("Kamil","Client", UUID.randomUUID(), true,"client1", passwordEncoder.encode("client")));
//        userRepo.add(new UserClientEnt("Maciek","ResourcesManager", UUID.randomUUID(), true,"manager",passwordEncoder.encode("manager")));


        trainRepo.add(new ExpressTrainEnt( "pociag1", 10, new RegioEnt(),5));
        trainRepo.add(new PassengerTrainEnt( "pociag2", 20, new InterCityEnt()));
        trainRepo.add(new ExpressTrainEnt( "pociag3", 30, new TLKEnt(),2));
        trainRepo.add(new PassengerTrainEnt( "pociag4", 30, new TLKEnt()));

        for (int i = 0; i < 3; i++) {
//            ticketRepo.add(new Ticket(UUID.randomUUID(),userRepo.getAll().get(i),trainRepo.getAll().get(i), LocalDate.of(1111,1,1),null));
//            ticketRepo.getAll().get(i).get().setTicket(ticketRepo.getAll().get(i));
//            ticketRepo.getAll().get(i).getUser().getAll().add(ticketRepo.getAll().get(i));
        }
    }
}
