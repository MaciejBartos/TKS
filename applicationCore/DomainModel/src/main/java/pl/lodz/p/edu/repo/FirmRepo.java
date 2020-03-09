package pl.lodz.p.edu.repo;

import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.model.Firms.InterCity;
import pl.lodz.p.edu.model.Firms.Regio;
import pl.lodz.p.edu.model.Firms.TLK;
import pl.lodz.p.edu.model.Firms.Firm;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FirmRepo implements IRepo<Firm> {

    private List<Firm> firms = new ArrayList<>();

    public FirmRepo() {
        firms.add(new TLK());
        firms.add(new Regio());
        firms.add(new InterCity());
    }

    public void add(Firm firm) {
        firms.add(firm);
    }

    public Optional<Firm> getById(UUID id) {
        return firms.stream().filter(firm -> firm.getFirmId().equals(id)).findFirst();
    }

    public List<Firm> getAll() {
        return firms;
    }

    public void delete(Firm f) {
        firms.remove(f);
    }

    public void update(Firm f) {
        Optional<Firm> firm = getById(f.getFirmId());
        if (firm.isPresent()){
            firms.set(firms.indexOf(firm), f);
        }
    }
}