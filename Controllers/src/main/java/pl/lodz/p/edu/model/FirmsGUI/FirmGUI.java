package pl.lodz.p.edu.model.FirmsGUI;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class FirmGUI {

    private UUID firmId;
    @NotBlank(message = "Firm cannot be empty")
    @Pattern(regexp = "(TLK)|(Regio)|(InterCity)", message = "Invalid Name")
    private String name;

    public FirmGUI(String name) {
        this.firmId = UUID.randomUUID();
        this.name = name;
    }

    public FirmGUI(){
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

    public void setFirmId(UUID id){
        this.firmId = id;
    }
}
