package pl.lodz.p.edu.repositories;

import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;
import pl.lodz.p.edu.data.FirmsEnt.InterCityEnt;
import pl.lodz.p.edu.data.FirmsEnt.RegioEnt;
import pl.lodz.p.edu.data.FirmsEnt.TLKEnt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FirmRepoEnt implements IRepoEnt<FirmEnt> {

    private List<FirmEnt> firms = new ArrayList<>();

    public FirmRepoEnt() {
        firms.add(new TLKEnt());
        firms.add(new RegioEnt());
        firms.add(new InterCityEnt());
    }

    public void add(FirmEnt firm) {
        firms.add(firm);
    }

    public Optional<FirmEnt> getById(UUID id) {
        return firms.stream().filter(firm -> firm.getFirmId().equals(id)).findFirst();
    }

    public List<FirmEnt> getAll() {
        return firms;
    }

    public void delete(FirmEnt f) {
        firms.remove(f);
    }

    public void update(FirmEnt f) {
        Optional<FirmEnt> firm = getById(f.getFirmId());
        if (firm.isPresent()){
            firms.set(firms.indexOf(firm), f);
        }
    }
}
