package pl.lodz.p.edu.data.UsersEnt.validationEnt;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lodz.p.edu.repo.UserRepo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class UniqueEmailValidatorEnt implements ConstraintValidator<UniqueEmailEnt, String> {

    @Autowired
    private UserRepo userRepository;



    public void initialize(UniqueEmailEnt constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userRepository.getByEmail(email).isPresent();
    }

}
