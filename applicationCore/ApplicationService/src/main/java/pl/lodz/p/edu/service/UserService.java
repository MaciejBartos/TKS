package pl.lodz.p.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.aggregates.UserRepositoryAdapter;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Users.Admin;
import pl.lodz.p.edu.model.Users.Client;
import pl.lodz.p.edu.model.Users.ResourcesManager;
import pl.lodz.p.edu.model.Users.User;
import pl.lodz.p.edu.repo.UserRepo;
import pl.lodz.p.edu.userInterface.*;

//import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements SaveItemUseCase<User>, UpgradeItemUseCase<User>, ShowItemUseCase<User>, ShowAllItemsUseCase<User>, SortItemsUseCase<User>, ChangeStateUseCase {

    //private IRepo userRepo;
    //private UserRepositoryAdapter userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IAddItem<User> userAdd;
    @Autowired
    private IUpdateItem<User> userUp;
    @Autowired
    private IGetAllItems<User> usersGet;
    @Autowired
    private IGetItem<User> userGet;
    @Autowired
    private ISortItems<User> userSort;
    @Autowired
    private ILoadUserByEmail userGetByEmail;

//    @Autowired
//    public UserService(UserRepositoryAdapter userRepo, PasswordEncoder passwordEncoder) {
//        this.passwordEncoder =passwordEncoder;
//        this.userRepo = userRepo;
//    }

    public void add(User u){
        switch(u.getType()){
            case "Admin":
                userAdd.add(new Admin(u.getName(),u.getUserId(),u.getIsActive(),u.getEmail(),passwordEncoder.encode(u.getPassword())));
                //userRepo.add(new Admin(u.getName(),u.getUserId(),u.getIsActive(),u.getEmail(),passwordEncoder.encode(u.getPassword())));
                break;
            case "ResourcesManager":
                userAdd.add(new ResourcesManager(u.getName(),u.getUserId(),u.getIsActive(),u.getEmail(),passwordEncoder.encode(u.getPassword())));
                //userRepo.add(new ResourcesManager(u.getName(),u.getUserId(),u.getIsActive(),u.getEmail(),passwordEncoder.encode(u.getPassword())));
                break;
            case "Client":
                userAdd.add(new Client(u.getName(),u.getUserId(),u.getIsActive(),u.getEmail(),passwordEncoder.encode(u.getPassword())));
                //userRepo.add(new Client(u.getName(),u.getUserId(),u.getIsActive(),u.getEmail(),passwordEncoder.encode(u.getPassword())));
                break;
        }
    }

    public List<User> getAll(){

        //return userRepo.getAll();
        return usersGet.getAll();
    }

    public User get(UUID id){
       Optional<User> u = userGet.getById(id);
//       Optional<User> u = userRepo.getById(id);
       if(u.isPresent()){
           return  u.get();
       }
       else {
           return new User();
       }

    }

    public User get(String email){
        //Optional<User> u = ((UserRepo)userRepo).getByEmail(email);
        Optional<User> u = ((UserRepo)userGetByEmail).getByEmail(email);
        if(u.isPresent()){
            return  u.get();
        }
        else {
            return new User();
        }
    }

    public void changeState(UUID id){
        Optional<User> u = userGet.getById(id);
        //Optional<User> u = userRepo.getById(id);
        if(u.isPresent()){
            u.get().changeState();
        }
    }


    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //userRepo.update(user);
        userUp.update(user);
//        Optional<User> u = userRepo.getById(user.getUserId());
//        if (u.isPresent()){
//            u.get().setName(user.getName());
//            u.get().setIsActive(user.getIsActive());
//            u.get().setType(user.getType());
//            userRepo.update(u.get().getUserId(),u.get());
//        }
    }
    // public void update(UUID id, User uupdate){
//        Optional<User> u = userRepo.getById(id);
//        if (u.isPresent()){
//            if(user.getName() !="") {
//                u.get().setName(user.getName());
//            }
//            if(user.getType() != "" ){
//                u.get().setType(user.getType());
//            }
//            u.get().setIsActive(user.getIsActive());
//            userRepo.update(id,u.get());
//        }
//    }
//    public void delete(UUID id){
//        //ustawienie nulla dla alokacji
//        userRepo.delete(id);
//    }

    public List<User> sort(String text){
        return userSort.sort(text);
        //return ((UserRepo)userRepo).sort(text);
    }
}
