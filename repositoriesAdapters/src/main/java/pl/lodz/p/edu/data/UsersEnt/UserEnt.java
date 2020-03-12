package pl.lodz.p.edu.data.UsersEnt;

import pl.lodz.p.edu.data.TicketsEnt.TicketEnt;
import pl.lodz.p.edu.data.UsersEnt.validationEnt.UniqueEmailEnt;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserEnt {


    @NotBlank(message = "Email cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9.]+@([a-zA-Z0-9]+[.])+[a-zA-Z]{2,4}$", message = "Invalid email")
    @UniqueEmailEnt(message = "Email is used")

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "[A-z]*", message = "Invalid Name")
    @Size(min = 2, max = 20, message = "Name must have between 2-20 letters")
    private String name;

    private String type;

    private UUID userId;
    private boolean isActive;

    public List<UUID> getTickets() {
        return tickets;
    }

    public void setTickets(List<UUID> tickets) {
        this.tickets = tickets;
    }

    private List<UUID> tickets = new ArrayList<>();

    public UserEnt(String name, String type, UUID idu, boolean isActive, String email, String password) {
        this.name = name;
        this.type = type;
        this.userId = idu;
        this.isActive = isActive;
        this.email = email;
        this.password = password;
    }

    public UserEnt(){
        this.isActive=true;
        this.userId = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public void changeState(){
        this.isActive = !this.isActive;
    }



}
