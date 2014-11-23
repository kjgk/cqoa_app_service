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

    @Resource(name = "req_instancelist_service")
    private BusiGateService instanceListService;

    @Resource(name = "req_committask_service")
    private BusiGateService commitTaskService;

    @Resource(name = "req_flownodeget_service")
    private BusiGateService flowNodeGetService;

    @Resource(name = "req_miscellaneoussubmit_service")
    private BusiGateService miscellaneousSubmitService;

    @Resource(name = "req_outgoingsubmit_service")
    private BusiGateService outgoingSubmitService;

    @Resource(name = "req_leavesubmit_service")
    private BusiGateService leaveSubmitService;

    @Resource(name = "req_trainingsubmit_service")
    private BusiGateService trainingSubmitService;

    @Resource(name = "req_carusesubmit_service")
    private BusiGateService carUseSubmitService;

    @Test
    public void testInstanceView() {

        Map params = new HashMap();
        params.put("objectId", "A1A04C89-8BE6-499B-9A12-DE5173EBA2B6");
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
        params.put("flowType", "Miscellaneous");
        User loginInfo = new User();
        loginInfo.setUserId("C33BE5FC-0CCE-4C7D-817F-7497FA0A8EA6");
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
        loginInfo.setUserId("C33BE5FC-0CCE-4C7D-817F-7497FA0A8EA6");
        try {
            Map result = taskHandledService.busi(params, loginInfo);

            Assert.assertNotNull(result.get("count"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testInstanceList() {

        Map params = new HashMap();
        params.put("currentPage", "1");
        params.put("pageSize", "5");
        params.put("complate", "1");
        params.put("flowType", "Leave");
        User loginInfo = new User();
        loginInfo.setUserId("C33BE5FC-0CCE-4C7D-817F-7497FA0A8EA6");
        try {
            Map result = instanceListService.busi(params, loginInfo);

            Assert.assertNotNull(result.get("result"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testInstanceTaskLog() {

        Map params = new HashMap();
        params.put("instanceId", "EF6CCF22-D8A6-43C4-8EAB-6ED228243053");
        User loginInfo = new User();
        loginInfo.setUserId("C33BE5FC-0CCE-4C7D-817F-7497FA0A8EA6");
        try {
            Map result = instanceTaskLogService.busi(params, loginInfo);

            Assert.assertNotNull(result.get("result"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testCommitTask() {

        Map params = new HashMap();
        params.put("taskId", "A12D989A-59EF-4285-9418-3360868EE722");
        params.put("approvers", "4ED21569-256E-4790-9C3B-2827B58A981B");
        params.put("opinion", "asdfsadf");
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
    public void testFlowNodeGet() {

        Map params = new HashMap();
        params.put("taskId", "0733DE31-9544-4525-824B-3C94C32D9476");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = flowNodeGetService.busi(params, loginInfo);

            Assert.assertNotNull(result.get("result"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testSubmitMiscellaneous() {

        Map params = new HashMap();
        params.put("description", "description");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = miscellaneousSubmitService.busi(params, loginInfo);

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
            Map result = outgoingSubmitService.busi(params, loginInfo);

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
            Map result = leaveSubmitService.busi(params, loginInfo);

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
            Map result = trainingSubmitService.busi(params, loginInfo);

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
            Map result = carUseSubmitService.busi(params, loginInfo);

            String message = (String) result.get("message");

            Assert.assertEquals(message, "1");

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
