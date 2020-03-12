package pl.lodz.p.edu.model.Users;

import pl.lodz.p.edu.model.Tickets.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {



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

    private String password;


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
