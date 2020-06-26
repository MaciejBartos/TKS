package pl.lodz.p.edu.userInterface;

import pl.lodz.p.edu.infrastructure.IDeleteItem;
import pl.lodz.p.edu.model.Users.User;


public interface IUserService extends ShowAllActiveUsersUseCase, ShowUserByNameUseCase, SaveItemUseCase<User>, UpgradeItemUseCase<User>, ShowItemUseCase<User>, ShowAllItemsUseCase<User>, SortItemsUseCase<User>, ChangeStateUseCase, DeleteUser {
}
