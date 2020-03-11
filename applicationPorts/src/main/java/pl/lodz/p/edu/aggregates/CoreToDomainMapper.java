package pl.lodz.p.edu.aggregates;

import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;
import pl.lodz.p.edu.model.Firms.Firm;

public class CoreToDomainMapper {
    public static FirmEnt firmMapping(Firm firm){
        return new FirmEnt(firm.getName());
    }

}
