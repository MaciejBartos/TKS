package pl.lodz.p.edu.userInterface;

import pl.lodz.p.edu.model.Clients.UserClient;


public interface IUserClientService extends ShowAllActiveUsersClientUseCase, ShowUserClientByNameUseCase, SaveItemUseCase<UserClient>, UpgradeItemUseCase<UserClient>, ShowItemUseCase<UserClient>, ShowAllItemsUseCase<UserClient>, SortItemsUseCase<UserClient>, ChangeStateClientUseCase, DeleteClientUseCase {
}
