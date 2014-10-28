package cn.net.withub.busiGate.ezy.service;

import cn.net.withub.busiGate.ezy.model.User;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class WorkflowTest extends BaseJunit4Test {

    @Resource(name = "req_instanceview_service")
    private BusiGateService instanceViewService;

    @Resource(name = "req_taskpending_service")
    private BusiGateService taskPendingService;

    @Resource(name = "req_instancetasklog_service")
    private BusiGateService instanceTaskLogService;

    @Test
    public void testInstanceView() {

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


    @Test
    public void testTaskPending() {

        Map params = new HashMap();
        params.put("currentPage", "1");
        params.put("pageSize", "5");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = taskPendingService.busi(params, loginInfo);

            Assert.assertNotNull(result.get("count"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testInstanceTaskLog() {

        Map params = new HashMap();
        params.put("instanceId", "0815A172-05F3-4875-9348-D80403A50733");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = instanceTaskLogService.busi(params, loginInfo);

            Assert.assertNotNull(result.get("resultMap"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }


}
