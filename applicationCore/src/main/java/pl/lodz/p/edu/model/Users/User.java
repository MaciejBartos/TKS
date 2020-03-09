package pl.lodz.p.edu.model.Users;

import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.model.Users.validation.UniqueEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {



    @NotBlank(message = "Email cannot be empty")
    //@Pattern(regexp = "^[a-zA-Z0-9.]+@([azA-Z0-9]+[.])*[a-zA-Z]{2,4}$", message = "Invalid email")
    @Pattern(regexp = "^[a-zA-Z0-9.]+@([a-zA-Z0-9]+[.])+[a-zA-Z]{2,4}$", message = "Invalid email")
    @UniqueEmail(message = "Email is used")
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    private List<Ticket> tickets = new ArrayList<>();

    public User(String name, String type, UUID idu, boolean isActive, String email, String password) {
        this.name = name;
        this.type = type;
        this.userId = idu;
        this.isActive = isActive;
        this.email = email;
        this.password = password;
    }

    public User(){
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
