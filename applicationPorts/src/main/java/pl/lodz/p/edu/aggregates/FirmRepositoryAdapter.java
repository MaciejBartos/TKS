package pl.lodz.p.edu.aggregates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;
import pl.lodz.p.edu.infrastructure.IGetAllItems;
import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.repositories.IRepoEnt;

import java.util.LinkedList;
import java.util.List;

@Repository
public class FirmRepositoryAdapter implements IGetAllItems<Firm> {

    @Autowired
    private IRepoEnt<FirmEnt> firmRepo;

    @Override
    public List<Firm> getAll() {
        List<Firm> firms = new LinkedList<>();
        for(FirmEnt firm: firmRepo.getAll()){
            firms.add(ToDomainConverter.convertFirm(firm));
        }
        return firms;
    }
}
