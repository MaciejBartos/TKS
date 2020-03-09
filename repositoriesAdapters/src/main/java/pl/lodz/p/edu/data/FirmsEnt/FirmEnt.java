package pl.lodz.p.edu.data.FirmsEnt;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class FirmEnt {
    private UUID firmId;
    @NotBlank(message = "FirmEnt cannot be empty")
    @Pattern(regexp = "(TLKEnt)|(RegioEnt)|(InterCityEnt)", message = "Invalid Name")
    private String name;

    public FirmEnt( String name) {
        this.firmId = UUID.randomUUID();
        this.name = name;
    }

    public FirmEnt(){
       this.firmId = UUID.randomUUID();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getFirmId() {
        return firmId;
    }
}
