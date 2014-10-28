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

    @Resource(name = "req_committask_service")
    private BusiGateService commitTaskService;

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

    @Test
    public void testCommitTask() {

        Map params = new HashMap();
        params.put("taskId", "A12D989A-59EF-4285-9418-3360868EE722");
        params.put("opinion", "È¥Íæ°É");
        params.put("approvers", "4ED21569-256E-4790-9C3B-2827B58A981B");
        params.put("handleResult", "Pass");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = commitTaskService.busi(params, loginInfo);

            String message = (String) result.get("message");

            Assert.assertEquals(message, "1");

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }


}
