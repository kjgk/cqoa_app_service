package cn.net.withub.busiGate.ezy.service;

import cn.net.withub.busiGate.ezy.model.User;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class TaskpPendingServiceTest extends BaseJunit4Test {

    @Resource(name = "req_taskpending_service")
    private BusiGateService taskPendingService;

    @Test
    public void testLogin() {

        Map params = new HashMap();
        params.put("currentPage", "1");
        params.put("pageSize", "5");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = taskPendingService.busi(params, loginInfo);

            Assert.assertNull(result.get("total"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
