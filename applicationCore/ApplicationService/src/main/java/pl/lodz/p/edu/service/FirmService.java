package pl.lodz.p.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.aggregates.FirmRepositoryAdapter;
import pl.lodz.p.edu.infrastructure.IGetAllItems;
import pl.lodz.p.edu.model.Firms.Firm;

import java.util.List;

@Service
public class FirmService {

    //private IRepo firmRepo;
    @Autowired
    private IGetAllItems<Firm> firms;

    public List<Firm> getFirms(){
        return firms.getAll();
    }
}
