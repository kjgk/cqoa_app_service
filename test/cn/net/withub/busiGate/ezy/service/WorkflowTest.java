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

    @Resource(name = "req_taskhandled_service")
    private BusiGateService taskHandledService;

    @Resource(name = "req_instancetasklog_service")
    private BusiGateService instanceTaskLogService;

    @Resource(name = "req_committask_service")
    private BusiGateService commitTaskService;

    @Resource(name = "req_submitmiscellaneous_service")
    private BusiGateService submitMiscellaneousService;

    @Resource(name = "req_submitoutgoing_service")
    private BusiGateService submitOutgoingService;

    @Resource(name = "req_submitleave_service")
    private BusiGateService submitLeaveService;

    @Resource(name = "req_submittraining_service")
    private BusiGateService submitTrainingService;

    @Resource(name = "req_submitcaruse_service")
    private BusiGateService submitCarUseService;

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
        params.put("flowType", "outgoing");
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
    public void testTaskHandled() {

        Map params = new HashMap();
        params.put("currentPage", "1");
        params.put("pageSize", "5");
        params.put("flowType", "outgoing");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = taskHandledService.busi(params, loginInfo);

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

    @Test
    public void testSubmitMiscellaneous() {

        Map params = new HashMap();
        params.put("description", "Íþ·çÍþ·ç");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = submitMiscellaneousService.busi(params, loginInfo);

            String message = (String) result.get("message");

            Assert.assertEquals(message, "1");

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testSubmitOutgoing() {

        Map params = new HashMap();

        params.put("beginDate","2014-10-01");
        params.put("endDate","2014-10-30");
        params.put("localCity","1");
        params.put("description","description");
        params.put("destination","destination");
        params.put("driveRoute","driveRoute");
        params.put("transportation","transportation");
        params.put("requiredCar","1");

        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = submitOutgoingService.busi(params, loginInfo);

            String message = (String) result.get("message");

            Assert.assertEquals(message, "1");

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testSubmitLeave() {

        Map params = new HashMap();

        params.put("beginDate","2014-10-01");
        params.put("endDate","2014-10-30");
        params.put("localCity","1");
        params.put("description","description");

        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = submitLeaveService.busi(params, loginInfo);

            String message = (String) result.get("message");

            Assert.assertEquals(message, "1");

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testSubmitTraining() {

        Map params = new HashMap();

        params.put("beginDate","2014-10-01");
        params.put("endDate","2014-10-30");
        params.put("address","address");
        params.put("content","content");
        params.put("peopleCount","100");
        params.put("publicity","publicity");
        params.put("target","target");

        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = submitTrainingService.busi(params, loginInfo);

            String message = (String) result.get("message");

            Assert.assertEquals(message, "1");

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testSubmitCarUse() {

        Map params = new HashMap();

        params.put("beginTime","2014-10-01 00:00");
        params.put("endTime","2014-10-30 23:59");
        params.put("address","address");
        params.put("localCity","1");
        params.put("description","description");

        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = submitCarUseService.busi(params, loginInfo);

            String message = (String) result.get("message");

            Assert.assertEquals(message, "1");

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
