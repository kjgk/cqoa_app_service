package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import com.withub.server.OAServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class UserListServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    @Autowired
    private OAServer oaServer;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        String organizationId = arg0.get("organizationId");

        Map<String, String> returnMap = new HashMap<String, String>();
        try {
            String result = oaServer.getUserList(organizationId);
            returnMap.put("result", result);
        } catch (Exception e) {
            throw new AppException(EzyErrorCode.EZY_QUERY_ERROR, "≤È—Ø ß∞‹");
        }
        return returnMap;
    }

    public void setJdbcTool(JdbcTool jdbcTool) {
        this.jdbcTool = jdbcTool;
    }

    public void setOaServer(OAServer oaServer) {
        this.oaServer = oaServer;
    }
}
