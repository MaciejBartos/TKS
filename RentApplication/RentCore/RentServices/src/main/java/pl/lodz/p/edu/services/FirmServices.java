package pl.lodz.p.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.infrastructure.IGetAllItems;
import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.userInterface.ShowAllItemsUseCase;

import java.util.List;

@Service
@ComponentScan("pl.lodz.p.edu.aggregates")
public class FirmServices implements ShowAllItemsUseCase<Firm> {

    private final IGetAllItems<Firm> firms;

    @Autowired
    public FirmServices(IGetAllItems<Firm> firms) {
        this.firms = firms;
    }

    public List<Firm> getAll() {
        return firms.getAll();
    }
}
