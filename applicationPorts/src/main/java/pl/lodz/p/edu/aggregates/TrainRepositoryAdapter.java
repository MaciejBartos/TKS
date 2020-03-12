package pl.lodz.p.edu.aggregates;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lodz.p.edu.data.TrainsEnt.TrainEnt;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.repositories.IRepoEnt;
import pl.lodz.p.edu.repositories.TrainRepoEnt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrainRepositoryAdapter implements IAddItem<Train>, IDeleteItem<Train>, IGetAllItems<Train>, IGetItem<Train>, ISortItems<Train>, IUpdateItem<Train> {

    @Autowired
    private IRepoEnt<TrainEnt> trainRepo;

    @Override
    public void add(Train item) {
        trainRepo.add(item);
    }

    @Override
    public void delete(Train item) {
        trainRepo.delete(item);
    }

    @Override
    public List<Train> getAll() {
        return trainRepo.getAll();
    }

    @Override
    public Optional<Train> getById(UUID id) {
        return trainRepo.getById(id);
    }

    @Override
    public List<Train> sort(String text) {
        return ((TrainRepoEnt)trainRepo).sort(text);
    }

    @Override
    public void update(Train item) {
        trainRepo.update(item);
    }
}
