package pl.lodz.p.edu.aggregates;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;
import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.prots.IGetAllItems;
import pl.lodz.p.edu.repositories.IRepoEnt;

import java.util.List;

public class FirmRepositoryAdapter implements IGetAllItems<FirmEnt> {

    @Autowired
    private IRepoEnt<FirmEnt> firmRepo;

    @Override
    public List<FirmEnt> getAll() {
        return firmRepo.getAll();
    }
}
