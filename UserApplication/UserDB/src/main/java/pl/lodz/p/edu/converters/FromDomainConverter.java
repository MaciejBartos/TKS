package pl.lodz.p.edu.converters;

import pl.lodz.p.edu.data.UsersEnt.UserEnt;
import pl.lodz.p.edu.model.Users.User;

public class FromDomainConverter {

    public static UserEnt convertUser(User userToConvert){
        UserEnt user = new UserEnt();
        user.setEmail(userToConvert.getEmail());
        user.setName(userToConvert.getName());
        user.setPassword(userToConvert.getPassword());
        user.setIsActive(userToConvert.getIsActive());
        user.setUserId(userToConvert.getUserId());
        //user.setTickets(userToConvert.getTickets());
        user.setType(userToConvert.getType());
        return user;
    }

}
