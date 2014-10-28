package cn.net.withub.busiGate.ezy.service;

import cn.net.withub.busiGate.ezy.model.User;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class UserTest extends BaseJunit4Test {

    @Resource(name = "req_userlist_service")
    private BusiGateService userListService;

    @Test
    public void testUserList() {

        Map params = new HashMap();
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = userListService.busi(params, loginInfo);

            Assert.assertNotNull(result.get("resultMap"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
