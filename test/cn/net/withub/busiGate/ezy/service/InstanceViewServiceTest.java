package cn.net.withub.busiGate.ezy.service;

import cn.net.withub.busiGate.ezy.model.User;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class InstanceViewServiceTest extends BaseJunit4Test {

    @Resource(name = "req_instanceview_service")
    private BusiGateService instanceViewService;

    @Test
    public void testLogin() {

        Map params = new HashMap();
        params.put("objectId", "0815A172-05F3-4875-9348-D80403A50733");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = instanceViewService.busi(params, loginInfo);

            Assert.assertNotNull(result.get("result"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
