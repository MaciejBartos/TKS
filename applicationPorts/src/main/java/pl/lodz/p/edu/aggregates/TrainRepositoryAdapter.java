package pl.lodz.p.edu.aggregates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.data.TrainsEnt.TrainEnt;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.repositories.IRepoEnt;
import pl.lodz.p.edu.repositories.TrainRepoEnt;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TrainRepositoryAdapter implements IAddItem<Train>, IDeleteItem<Train>, IGetAllItems<Train>, IGetItem<Train>, ISortItems<Train>, IUpdateItem<Train> {

    @Autowired
    private IRepoEnt<TrainEnt> trainRepo;

    @Override
    public void add(Train item) {
        trainRepo.add(FromDomainConverter.convertTrain(item));
    }

    @Override
    public void delete(Train item) {
        trainRepo.delete(FromDomainConverter.convertTrain(item));
    }

    @Override
    public List<Train> getAll() {
        List<Train> trains = new LinkedList<>();
        for(TrainEnt train: trainRepo.getAll()){
            trains.add(ToDomainConverter.convertTrain(train));
        }
        return trains;
    }

    @Override
    public Optional<Train> getById(UUID id) {
        return Optional.of(ToDomainConverter.convertTrain(trainRepo.getById(id).get()));
    }

    @Override
    public List<Train> sort(String text) {
        List<Train> trains = new LinkedList<>();
        for(TrainEnt train: ((TrainRepoEnt)trainRepo).sort(text)){
            trains.add(ToDomainConverter.convertTrain(train));
        }
        return trains;
    }

    @Override
    public void update(Train item) {
        trainRepo.update(FromDomainConverter.convertTrain(item));
    }
}
