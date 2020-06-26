package pl.lodz.p.edu.repositories;

import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.data.TrainsEnt.ExpressTrainEnt;
import pl.lodz.p.edu.data.TrainsEnt.TrainEnt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TrainRepoEnt implements IRepoEnt<TrainEnt> {

    private List<TrainEnt> trains = new ArrayList<>();

    public TrainRepoEnt() {
//        trains.add(new ExpressTrain(UUID.randomUUID(), "pociag1", 10, new Regio(),5));
//        trains.add(new PassengerTrain(UUID.randomUUID(), "pociag2", 20, new InterCity()));
//        trains.add(new ExpressTrain(UUID.randomUUID(), "pociag3", 30, new TLK(),2));
//        trains.add(new PassengerTrain(UUID.randomUUID(), "pociag4", 30, new TLK()));

    }

    public void add(TrainEnt t) {
        if(!getById(t.getTrainId()).isPresent()) {
            synchronized (this) {
                trains.add(t);
            }
        }
        //trains.add(new Train(UUID.randomUUID(),name));
    }

    public Optional<TrainEnt> getById(UUID id) {
        return trains.stream().filter(user -> user.getTrainId().equals(id)).findFirst();
    }

    public List<TrainEnt> getAll() {
        return new ArrayList<>(trains);
    }

    public void delete(TrainEnt t) {
       synchronized (this){
           Optional<TrainEnt> train = getById(t.getTrainId());
           if(train.isPresent()){
               trains.remove(train.get());
           }
       }
    }

    public void update(TrainEnt t) {
        Optional<TrainEnt> train = getById(t.getTrainId());
        if (train.isPresent()) {
            synchronized (this) {
                train.get().setSeats(t.getSeats());
                train.get().setFirm(t.getFirm());
                train.get().setName(t.getName());
                train.get().setTicketID(t.getTicketID());
                if (train.get() instanceof ExpressTrainEnt && t instanceof ExpressTrainEnt) {
                    ((ExpressTrainEnt) train.get()).setCarriage(((ExpressTrainEnt) t).getCarriage());
                }
            }
        }
    }

    public List<TrainEnt> sort(String text){
        List<TrainEnt> sortTrains = new ArrayList<>();
        for (TrainEnt t: trains
        ) {
            if (t.getName().length() >= text.length()) {
                if (t.getName().substring(0, text.length()).equals(text)) {
                    sortTrains.add(t);
                }
            }

        }
        return sortTrains;
    }
}

