package cn.net.withub.busiGate.ezy.service;

import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class UserTest extends BaseJunit4Test {

    @Resource(name = "req_organizationlist_service")
    private BusiGateService organizationListService;

    @Resource(name = "req_userlist_service")
    private BusiGateService userListService;


    @Test
    public void testOrganizationList() {

        try {
            Map result = organizationListService.busi(null, null);

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
    public void testUserList() {

        Map params = new HashMap();
        params.put("organizationId","9C79A686-77AF-4528-9804-7742860579FD");

        try {
            Map result = userListService.busi(params, null);

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
