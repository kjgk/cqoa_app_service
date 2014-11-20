package cn.net.withub.busiGate.ezy.service;

import cn.net.withub.busiGate.ezy.model.User;
import cn.net.withub.busiGate.ezy.service.imp.OrganizationServiceImp;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class UserTest extends BaseJunit4Test {

//    @Resource(name = "req_userlist_service")
//    private BusiGateService userListService;

    @Resource(name = "req_organization_service")
    private BusiGateService organizationService;

    @Resource(name = "req_user_service")
    private BusiGateService userService;


    @Test
    public void testOrganizationList() {

        try {
            Map result = organizationService.busi(null, null);

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
            Map result = userService.busi(params, null);

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
