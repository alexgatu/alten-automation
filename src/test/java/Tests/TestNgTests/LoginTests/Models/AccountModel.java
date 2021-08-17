package Tests.TestNgTests.LoginTests.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AccountModel {

    private String username;
    private String password;

    public AccountModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
