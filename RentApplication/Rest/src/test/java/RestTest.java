//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.FieldError;
//import pl.lodz.p.edu.converter.ToDomainConverterRest;
//import pl.lodz.p.edu.model.Firms.Firm;
//import pl.lodz.p.edu.model.FirmsRest.FirmRest;
//import pl.lodz.p.edu.model.Trains.ExpressTrain;
//import pl.lodz.p.edu.model.Trains.PassengerTrain;
//import pl.lodz.p.edu.model.Trains.Train;
//import pl.lodz.p.edu.model.TrainsRest.ExpressTrainRest;
//import pl.lodz.p.edu.model.TrainsRest.PassengerTrainRest;
//import pl.lodz.p.edu.model.TrainsRest.TrainRest;
//import pl.lodz.p.edu.rest.TrainRestController;
//import pl.lodz.p.edu.services.TrainServices;
//import pl.lodz.p.edu.userInterface.*;
//
//import java.util.ArrayList;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//
//public class RestTest {
//
//     ShowAllItemsUseCase<Train> trainsGet = Mockito.mock(TrainServices.class);
//     ShowItemUseCase<Train> trainGet = Mockito.mock(TrainServices.class);
//     SaveItemUseCase<Train> trainAdd = Mockito.mock(TrainServices.class);
//     UpgradeItemUseCase<Train> trainUp = Mockito.mock(TrainServices.class);
//     SortItemsUseCase<Train> trainSort = Mockito.mock(TrainServices.class);
//     RemoveTrainUseCase trainDel = Mockito.mock(TrainServices.class);
//
//     List<Train> trains;
//     List<Train> trains2;
//     Train trainToAdd = new Train();
//
//
//    @InjectMocks
//    private TrainRestController trainRestController = new TrainRestController(trainsGet,trainGet,trainAdd,trainUp,trainSort,trainDel);
//
//    @BeforeEach
//    void setMockOutput() throws Exception {
//        trains = new ArrayList<>();
//        trains2 = new ArrayList<>();
//        trains.add(new ExpressTrain("pociag1", 10, new Firm(), 33));
//        trains.add(new PassengerTrain("pociag2", 12, new Firm()));
//        trains2.add(new PassengerTrain("pociag2", 12, new Firm()));
//        when(trainsGet.getAll()).thenReturn(trains);
//        updateList();
//        when(trainSort.sort("pociag")).thenReturn(trains);
//        when(trainSort.sort("pociag2")).thenReturn(trains2);
//        when(trainSort.sort("kekw")).thenReturn(new ArrayList<>());
//
//    }
//
//    void updateList(){
//        for(Train train: trainsGet.getAll()
//        ){
//            when(trainGet.get(train.getTrainId())).thenReturn(train);
//        }
//    }
//
//    @Test
//    void testGetAll() {
//        assertEquals(2,trainRestController.getAll().size());
//        assertTrue(trainRestController.getAll().get(0) instanceof ExpressTrainRest);
//        assertTrue(trainRestController.getAll().get(1) instanceof PassengerTrainRest);
//    }
//
//    @Test
//    void testGetExpress() {
//        TrainRest train = trainRestController.get(trains.get(0).getTrainId());
//        assertEquals(trains.get(0).getName(),train.getName());
//        assertEquals(trains.get(0).getSeats(),train.getSeats());
//        assertTrue(train instanceof ExpressTrainRest);
//        assertEquals(((ExpressTrain)trains.get(0)).getCarriage(),((ExpressTrainRest)train).getCarriage());
//    }
//
//    @Test
//    void testGetPassenger() {
//        TrainRest train = trainRestController.get(trains.get(1).getTrainId());
//        assertEquals(trains.get(1).getName(),train.getName());
//        assertEquals(trains.get(1).getSeats(),train.getSeats());
//        assertTrue(train instanceof PassengerTrainRest);
//    }
//
//    @Test
//    void testAddExpress() throws Exception {
//        ExpressTrainRest trainRest = new ExpressTrainRest("pociag3", 13, new FirmRest(), 333);
//        BeanPropertyBindingResult bd = new BeanPropertyBindingResult(trainRest,"trainRest");
//        assertEquals(new ResponseEntity(HttpStatus.CREATED),trainRestController.postE(trainRest,bd));
//        trains.add(ToDomainConverterRest.convertTrain(trainRest));
//        assertEquals(3,trainRestController.getAll().size());
//        trainRest.setName("");
//        bd.addError(new FieldError("trainRest","name","nazwa nie moze byc pusta"));
//        assertEquals(new ResponseEntity(HttpStatus.BAD_REQUEST),trainRestController.postE(trainRest,bd));
//        assertEquals(3,trainRestController.getAll().size());
//
//        updateList();
//        TrainRest train = trainRestController.get(trains.get(2).getTrainId());
//        assertEquals(trains.get(2).getName(),train.getName());
//        assertEquals(trains.get(2).getSeats(),train.getSeats());
//        assertTrue(train instanceof ExpressTrainRest);
//        assertEquals(((ExpressTrain)trains.get(2)).getCarriage(),((ExpressTrainRest)train).getCarriage());
//    }
//
//    @Test
//    void testAddPassenger() throws Exception {
//        PassengerTrainRest trainRest = new PassengerTrainRest("pociag3", 13, new FirmRest());
//        BeanPropertyBindingResult bd = new BeanPropertyBindingResult(trainRest,"trainRest");
//        assertEquals(new ResponseEntity(HttpStatus.CREATED),trainRestController.postP(trainRest,bd));
//        trains.add(ToDomainConverterRest.convertTrain(trainRest));
//        assertEquals(3,trainRestController.getAll().size());
//        trainRest.setName("");
//        bd.addError(new FieldError("trainRest","name","nazwa nie moze byc pusta"));
//        assertEquals(new ResponseEntity(HttpStatus.BAD_REQUEST),trainRestController.postP(trainRest,bd));
//        assertEquals(3,trainRestController.getAll().size());
//
//        updateList();
//        TrainRest train = trainRestController.get(trains.get(2).getTrainId());
//        assertEquals(trains.get(2).getName(),train.getName());
//        assertEquals(trains.get(2).getSeats(),train.getSeats());
//        assertTrue(train instanceof PassengerTrainRest);
//    }
//
//    @Test
//    void testEditExpress() throws Exception {
//        ExpressTrainRest trainRest = new ExpressTrainRest("pociag3", 13, new FirmRest(), 333);
//        BeanPropertyBindingResult bd = new BeanPropertyBindingResult(trainRest,"trainRest");
//        assertEquals(new ResponseEntity(HttpStatus.OK),trainRestController.putE(trains.get(0).getTrainId(),trainRest,bd));
//        trains.get(0).setName(trainRest.getName());
//        trains.get(0).setSeats(trainRest.getSeats());
//        ((ExpressTrain)trains.get(0)).setCarriage(trainRest.getCarriage());
//        bd.addError(new FieldError("trainRest","name","nazwa nie moze byc pusta"));
//        assertEquals(new ResponseEntity(HttpStatus.BAD_REQUEST),trainRestController.putE(trains.get(0).getTrainId(),trainRest,bd));
//
//        TrainRest train = trainRestController.get(trains.get(0).getTrainId());
//        assertEquals(trainRest.getName(),train.getName());
//        assertEquals(trainRest.getSeats(),train.getSeats());
//        assertTrue(train instanceof ExpressTrainRest);
//        assertEquals(trainRest.getCarriage(),((ExpressTrainRest)train).getCarriage());
//    }
//
//    @Test
//    void testEditPassanger() throws Exception {
//        PassengerTrainRest trainRest = new PassengerTrainRest("pociag3", 13, new FirmRest());
//        BeanPropertyBindingResult bd = new BeanPropertyBindingResult(trainRest,"trainRest");
//        assertEquals(new ResponseEntity(HttpStatus.OK),trainRestController.putP(trains.get(1).getTrainId(),trainRest,bd));
//        trains.get(1).setName(trainRest.getName());
//        trains.get(1).setSeats(trainRest.getSeats());
//        bd.addError(new FieldError("trainRest","name","nazwa nie moze byc pusta"));
//        assertEquals(new ResponseEntity(HttpStatus.BAD_REQUEST),trainRestController.putP(trains.get(1).getTrainId(),trainRest,bd));
//
//        TrainRest train = trainRestController.get(trains.get(1).getTrainId());
//        assertEquals(trainRest.getName(),train.getName());
//        assertEquals(trainRest.getSeats(),train.getSeats());
//        assertTrue(train instanceof PassengerTrainRest);
//    }
//
//    @Test
//    void testDel(){
//        assertEquals(new ResponseEntity(HttpStatus.OK),trainRestController.del(trains.get(1).getTrainId()));
//        trains.remove(1);
//        assertEquals(1,trainRestController.getAll().size());
//        assertTrue(trainRestController.getAll().get(0) instanceof ExpressTrainRest);
//    }
//
//    @Test
//    void testSort(){
//        List<TrainRest> list = trainRestController.sort("pociag");
//        assertEquals(2,list.size());
//        for(TrainRest train: list
//            ){
//            assertEquals("pociag",train.getName().substring(0,"pociag".length()));
//        }
//        list = trainRestController.sort("pociag2");
//        assertEquals(1,list.size());
//        for(TrainRest train: list
//        ){
//            assertEquals("pociag2",train.getName().substring(0,"pociag2".length()));
//        }
//        list = trainRestController.sort("kekw");
//        assertEquals(0,list.size());
//
//    }
//
//
//
//}
