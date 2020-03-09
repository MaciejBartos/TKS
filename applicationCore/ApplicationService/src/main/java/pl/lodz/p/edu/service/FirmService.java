package pl.lodz.p.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.control.IGetAllItems;
import pl.lodz.p.edu.control.IGetItem;
import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.repo.IRepo;
import java.util.List;

@Service
public class FirmService {

    //private IRepo firmRepo;
    private IGetAllItems<Firm> getFirms;

    @Autowired
    public FirmService(IGetAllItems<Firm> getFirms) {
        this.getFirms = getFirms;
    }

    public List<Firm> getFirms(){
        return getFirms.getAll();
    }
}
