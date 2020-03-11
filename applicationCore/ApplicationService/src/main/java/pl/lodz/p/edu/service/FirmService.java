package pl.lodz.p.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.aggregates.FirmRepositoryAdapter;
import pl.lodz.p.edu.prots.IGetAllItems;
import pl.lodz.p.edu.model.Firms.Firm;

import java.util.List;

@Service
public class FirmService {

    //private IRepo firmRepo;
    @Autowired
    private FirmRepositoryAdapter firmRepo;

    @Autowired
    public FirmService(FirmRepositoryAdapter firmRepo) {
        this.firmRepo = firmRepo;
    }

    public List<Firm> getFirms(){
        return firmRepo.getAll();
    }
}
