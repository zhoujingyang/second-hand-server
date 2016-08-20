package com.rock.power.secondhand.server.test;

import com.rock.power.secondhand.server.service.LoginService;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by yanshi on 16/8/5.
 */
public class LoginServerTest {

    @Test
    public void testLogin() throws IOException {
        LoginService loginService = new LoginService();
        loginService.login("15674994799@mingzheng","63578021");
    }
}
