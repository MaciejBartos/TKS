package pl.lodz.p.edu.aggregates;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lodz.p.edu.data.TrainsEnt.TrainEnt;
import pl.lodz.p.edu.prots.*;
import pl.lodz.p.edu.repositories.IRepoEnt;
import pl.lodz.p.edu.repositories.TrainRepoEnt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrainRepositoryAdapter implements IAddItem<TrainEnt>, IDeleteItem<TrainEnt>, IGetAllItems<TrainEnt>, IGetItem<TrainEnt>, ISortItems<TrainEnt>, IUpdateItem<TrainEnt>{

    @Autowired
    private IRepoEnt<TrainEnt> trainRepo;

    @Override
    public void add(TrainEnt item) {
        trainRepo.add(item);
    }

    @Override
    public void delete(TrainEnt item) {
        trainRepo.delete(item);
    }

    @Override
    public List<TrainEnt> getAll() {
        return trainRepo.getAll();
    }

    @Override
    public Optional<TrainEnt> getById(UUID id) {
        return trainRepo.getById(id);
    }

    @Override
    public List<TrainEnt> sort(String text) {
        return ((TrainRepoEnt)trainRepo).sort(text);
    }

    @Override
    public void update(TrainEnt item) {
        trainRepo.update(item);
    }
}
