package pl.lodz.p.edu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.converter.FromDomainConverterRest;
import pl.lodz.p.edu.converter.ToDomainConverterRest;
import pl.lodz.p.edu.model.TrainsRest.ExpressTrainRest;
import pl.lodz.p.edu.model.TrainsRest.PassengerTrainRest;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.model.TrainsRest.TrainRest;
import pl.lodz.p.edu.userInterface.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trains")
public class TrainRestController {

    //private final TrainService trainService;

    private final ShowAllItemsUseCase<Train> trainsGet;
    private final ShowItemUseCase<Train> trainGet;
    private final SaveItemUseCase<Train> trainAdd;
    private final UpgradeItemUseCase<Train> trainUp;
    private final SortItemsUseCase<Train> trainSort;
    private final RemoveTrainUseCase trainDel;
     private final ShowNoAllocatedTrainsUseCase noAllocatedTrains;

    @Autowired
    public TrainRestController(ShowAllItemsUseCase<Train> trainsGet, ShowItemUseCase<Train> trainGet, SaveItemUseCase<Train> trainAdd, UpgradeItemUseCase<Train> trainUp, SortItemsUseCase<Train> trainSort, RemoveTrainUseCase trainDel, ShowNoAllocatedTrainsUseCase noAllocatedTrains) {
        this.trainsGet = trainsGet;
        this.trainGet = trainGet;
        this.trainAdd = trainAdd;
        this.trainUp = trainUp;
        this.trainSort = trainSort;
        this.trainDel = trainDel;
        this.noAllocatedTrains = noAllocatedTrains;
    }

//    @Autowired
//    public TrainRestController(ShowAllItemsUseCase<Train> t){
//        this.trainsGet = t;
//    }

//    @Autowired
//    public TrainRest(TrainService trainService) {
//        this.trainService = trainService;
//    }


    @GetMapping
    public List<TrainRest> getAll() {
        return FromDomainConverterRest.convertTrainsList(trainsGet.getAll());
    }

    @GetMapping(path = "{id}")
    public TrainRest get(@PathVariable UUID id) {
        return FromDomainConverterRest.convertTrain(trainGet.get(id));
    }

    @PostMapping("/express")
    public ResponseEntity postE(@Valid @RequestBody ExpressTrainRest train, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        trainAdd.add(ToDomainConverterRest.convertTrain(train));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/passenger")
    public ResponseEntity postP(@Valid @RequestBody PassengerTrainRest train, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        trainAdd.add(ToDomainConverterRest.convertTrain(train));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/express/{id}")
    public ResponseEntity putE(@PathVariable UUID id, @Valid @RequestBody ExpressTrainRest train, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        train.setTrainId(id);
        trainUp.update(ToDomainConverterRest.convertTrain(train));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/passenger/{id}")
    public ResponseEntity putP(@PathVariable UUID id, @Valid @RequestBody PassengerTrainRest train, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        train.setTrainId(id);
        trainUp.update(ToDomainConverterRest.convertTrain(train));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity del(@PathVariable UUID id) {
        trainDel.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/sort/{text}")
    public List<TrainRest> sort(@PathVariable String text) {
        return FromDomainConverterRest.convertTrainsList(trainSort.sort(text));
    }

    @GetMapping("notAllocated")
    public List<Train> noAllocated(){
        return noAllocatedTrains.getNoAllocatedTrains();
    }
}
