package cn.net.withub.busiGate.ezy.service;

import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class LoginTest extends BaseJunit4Test {

    @Resource(name = "req_login_service")
    private BusiGateService loginService;

    @Test
    public void testLogin() {

        Map params = new HashMap();
        params.put("username", "admin");
        params.put("password", "123456");
        try {
            Map result = loginService.busi(params, null);

            String message = (String) result.get("message");

            Assert.assertEquals(message, "1");

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
