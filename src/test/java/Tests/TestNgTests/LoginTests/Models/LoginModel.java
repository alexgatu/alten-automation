package Tests.TestNgTests.LoginTests.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LoginModel {

    private AccountModel account;
    private String userError;
    private String passwordError;
    private String generalError;

    public LoginModel(AccountModel account, String userError, String passwordError, String generalError) {
        this.account = account;
        this.userError = userError;
        this.passwordError = passwordError;
        this.generalError = generalError;
    }
}
