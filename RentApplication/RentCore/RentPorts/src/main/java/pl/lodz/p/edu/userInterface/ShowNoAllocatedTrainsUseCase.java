package pl.lodz.p.edu.userInterface;
import pl.lodz.p.edu.model.Trains.Train;

import java.util.List;


public interface ShowNoAllocatedTrainsUseCase {
    List<Train> getNoAllocatedTrains();
}
