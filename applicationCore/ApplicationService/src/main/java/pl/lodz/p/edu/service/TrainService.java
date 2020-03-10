package pl.lodz.p.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.control.ICRUDSItem;
import pl.lodz.p.edu.model.Firms.InterCity;
import pl.lodz.p.edu.model.Firms.Regio;
import pl.lodz.p.edu.model.Firms.TLK;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.repo.IRepo;
import pl.lodz.p.edu.repo.TrainRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainService {

    //private IRepo trainRepo;
    private ICRUDSItem<Train> trainRepo;

    @Autowired
    public TrainService(ICRUDSItem trainRepo) {
        this.trainRepo = trainRepo;
    }

    public void addTrain(Train t){

        trainRepo.add(t);
    }

    public List<Train> getTrains(){
        return trainRepo.getAll();
    }

    public Train getTrain(UUID id){
        Optional<Train> t = trainRepo.getById(id);
        if (t.isPresent()){
            return t.get();
        }
        return null;
        //return new Train();
    }

    public void updateTrain( Train tupdate){
        switch (tupdate.getFirm().getName()){
            case "TLK":
                tupdate.setFirm(new TLK());
                break;
            case "InterCity":
                tupdate.setFirm(new InterCity());
                break;
            case "Regio":
                tupdate.setFirm(new Regio());
                break;
        }
        trainRepo.update(tupdate);

    }
    public void delete(UUID id){
        //ustawienie nulla dla alokacji
        Optional<Train> t = trainRepo.getById(id);
        if(t.isPresent()){
//            if(t.get().getTicketID() !=null) {
//                t.get().getTicket().setTrain(null);
//
//            }
            trainRepo.delete(t.get());
        }

    }

    public List<Train> sort(String text){
        return ((TrainRepo)trainRepo).sort(text);
    }


}
