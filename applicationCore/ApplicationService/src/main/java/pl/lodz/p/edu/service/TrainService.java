package pl.lodz.p.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Firms.InterCity;
import pl.lodz.p.edu.model.Firms.Regio;
import pl.lodz.p.edu.model.Firms.TLK;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.repo.TrainRepo;
import pl.lodz.p.edu.userInterface.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainService implements SaveItemUseCase<Train>, UpgradeItemUseCase<Train>, ShowItemUseCase<Train>, ShowAllItemsUseCase<Train>, SortItemsUseCase<Train>, RemoveItemUseCase {

    //private IRepo trainRepo;
    //private TrainRepositoryAdapter trainRepo;
    @Autowired
    private IAddItem<pl.lodz.p.edu.model.Trains.Train> trainAdd;
    @Autowired
    private IGetAllItems<pl.lodz.p.edu.model.Trains.Train> trainsGet;
    @Autowired
    private IGetItem<pl.lodz.p.edu.model.Trains.Train> trainGet;
    @Autowired
    private IDeleteItem<pl.lodz.p.edu.model.Trains.Train> trainDel;
    @Autowired
    private IUpdateItem<pl.lodz.p.edu.model.Trains.Train> trainUp;
    @Autowired
    private ISortItems<pl.lodz.p.edu.model.Trains.Train> trainSort;

//    @Autowired
//    public TrainService(TrainRepositoryAdapter trainRepo) {
//        this.trainRepo = trainRepo;
//    }

    public void add(pl.lodz.p.edu.model.Trains.Train t){

        trainAdd.add(t);
    }

    public List<pl.lodz.p.edu.model.Trains.Train> getAll(){
        return trainsGet.getAll();
    }

    public pl.lodz.p.edu.model.Trains.Train get(UUID id){
        Optional<pl.lodz.p.edu.model.Trains.Train> t = trainGet.getById(id);
        if (t.isPresent()){
            return t.get();
        }
        return null;
        //return new Train();
    }

    public void update(pl.lodz.p.edu.model.Trains.Train tupdate){
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
        trainUp.update(tupdate);

    }
    public void delete(UUID id){
        //ustawienie nulla dla alokacji
        Optional<pl.lodz.p.edu.model.Trains.Train> t = trainGet.getById(id);
        if(t.isPresent()){
//            if(t.get().getTicketID() !=null) {
//                t.get().get().setTrain(null);
//
//            }
            trainDel.delete(t.get());
        }

    }

    public List<pl.lodz.p.edu.model.Trains.Train> sort(String text){
        return trainSort.sort(text);
    }


}
