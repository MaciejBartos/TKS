package pl.lodz.p.edu.model.Firms;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.UUID;

public class Firm {
    private UUID firmId;
    private String name;

    public Firm(@JsonProperty("name") String name) {
        this.firmId = UUID.randomUUID();
        this.name = name;
    }

    public Firm(){
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
