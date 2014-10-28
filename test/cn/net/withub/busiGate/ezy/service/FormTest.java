package cn.net.withub.busiGate.ezy.service;

import cn.net.withub.busiGate.ezy.model.User;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class FormTest extends BaseJunit4Test {

    @Resource(name = "req_miscellaneousview_service")
    private BusiGateService miscellaneousViewService;

    @Resource(name = "req_trainingview_service")
    private BusiGateService trainingViewService;

    @Resource(name = "req_leaveview_service")
    private BusiGateService leaveViewService;

    @Resource(name = "req_outgoingview_service")
    private BusiGateService outgoingViewService;

    @Resource(name = "req_caruseview_service")
    private BusiGateService carUseViewService;

    @Test
    public void testMiscellaneousView() {

        Map params = new HashMap();
        params.put("objectId", "04E1B60E-E71C-4BDF-BA62-7A6395532412");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = miscellaneousViewService.busi(params, loginInfo);

            System.out.println("===================================================");
            System.out.println(result.toString());
            System.out.println("===================================================");

            Assert.assertNotNull(result.get("result"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testTrainingView() {

        Map params = new HashMap();
        params.put("objectId", "742CE584-71EE-426B-A55F-4350AB1FEAF9");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = trainingViewService.busi(params, loginInfo);

            System.out.println("===================================================");
            System.out.println(result.toString());
            System.out.println("===================================================");

            Assert.assertNotNull(result.get("result"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testLeaveView() {

        Map params = new HashMap();
        params.put("objectId", "15D5DBE1-4F69-45CE-9C11-A091C2CAA5B3");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = leaveViewService.busi(params, loginInfo);

            System.out.println("===================================================");
            System.out.println(result.toString());
            System.out.println("===================================================");

            Assert.assertNotNull(result.get("result"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void outgoingLeaveView() {

        Map params = new HashMap();
        params.put("objectId", "1B66A231-F24C-4536-AFDB-CD8511EF43A8");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = outgoingViewService.busi(params, loginInfo);

            System.out.println("===================================================");
            System.out.println(result.toString());
            System.out.println("===================================================");

            Assert.assertNotNull(result.get("result"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void carUseLeaveView() {

        Map params = new HashMap();
        params.put("objectId", "46ADED4A-6A15-4235-8267-CEBEFE4E5D46");
        User loginInfo = new User();
        loginInfo.setUserId("053E0687-EF24-4E46-91BE-DA65A198F001");
        try {
            Map result = carUseViewService.busi(params, loginInfo);

            System.out.println("===================================================");
            System.out.println(result.toString());
            System.out.println("===================================================");

            Assert.assertNotNull(result.get("result"));

        } catch (AppException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
