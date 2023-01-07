package Lim.boardApp.form;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
//로그인을 위한 form -> 아이디와 비밀번호만 필요함
public class LoginForm {
    private String loginId;
    private String password;

    public LoginForm() {
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
}
