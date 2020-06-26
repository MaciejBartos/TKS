package pl.lodz.p.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Firms.InterCity;
import pl.lodz.p.edu.model.Firms.Regio;
import pl.lodz.p.edu.model.Firms.TLK;
import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.userInterface.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainServices implements ShowNoAllocatedTrainsUseCase, SaveItemUseCase<Train>, UpgradeItemUseCase<Train>, ShowItemUseCase<Train>, ShowAllItemsUseCase<Train>, SortItemsUseCase<Train>, RemoveTrainUseCase {

    private final IAddItem<Train> trainAdd;
    private final IGetAllItems<Train> trainsGet;
    private final IGetItem<Train> trainGet;
    private final IDeleteItem<Train> trainDel;
    private final IUpdateItem<Train> trainUp;
    private final ISortItems<Train> trainSort;
    private final IGetItem<Ticket> ticketGet;
    private final IUpdateItem<Ticket> ticketUp;

    public TrainServices(IAddItem<Train> trainAdd, IGetAllItems<Train> trainsGet, IGetItem<Train> trainGet, IDeleteItem<Train> trainDel, IUpdateItem<Train> trainUp, ISortItems<Train> trainSort, IGetItem<Ticket> ticketGet, IUpdateItem<Ticket> ticketUp) {
        this.trainAdd = trainAdd;
        this.trainsGet = trainsGet;
        this.trainGet = trainGet;
        this.trainDel = trainDel;
        this.trainUp = trainUp;
        this.trainSort = trainSort;
        this.ticketGet = ticketGet;
        this.ticketUp = ticketUp;
    }

    public void add(Train t) {
        try {
            trainAdd.add(t);
        } catch (Exception ignore) {
        }
    }

    public List<Train> getAll() {
        return trainsGet.getAll();
    }

    public Train get(UUID id) {
        Optional<Train> t = trainGet.getById(id);
        if (t.isPresent()) {
            return t.get();
        }
        return null;
    }

    public void update(Train tupdate) {
        switch (tupdate.getFirm().getName()) {
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
        try {
            trainUp.update(tupdate);
        } catch (Exception ignore) {
        }
    }

    public void delete(UUID id) {
        Train t = get(id);
        if (t != null) {
            if (t.getTicketID() != null) {
                Optional<Ticket> ticket = ticketGet.getById(id);
                ticket.get().setTrain(null);
                try {
                    ticketUp.update(ticket.get());
                } catch (Exception ignore) {
                }

            }
            try {
                trainDel.delete(t);
            } catch (Exception ignore) {
            }
        }
    }

    public List<Train> sort(String text) {
        return trainSort.sort(text);
    }

    public List<Train> getNoAllocatedTrains() {
        List<Train> t = new ArrayList<>();
        for (Train train :
                trainsGet.getAll()) {
            if (train.getTicketID() == null) {
                t.add(train);
            }
        }
        return t;
    }
}
