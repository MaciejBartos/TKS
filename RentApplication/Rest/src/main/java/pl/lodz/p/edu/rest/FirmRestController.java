package pl.lodz.p.edu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.userInterface.ShowAllItemsUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/v1/firms")
public class FirmRestController {

    private ShowAllItemsUseCase<Firm> firms;

    @Autowired
    public FirmRestController(ShowAllItemsUseCase<Firm> firms) {
        this.firms = firms;
    }

    @GetMapping
    public List<Firm> getAll(){
        return firms.getAll();
    }
}
