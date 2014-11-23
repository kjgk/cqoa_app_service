package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import com.withub.server.OAServer;

import java.util.HashMap;
import java.util.Map;

public class LeaveViewServiceImp implements BusiGateService {

    private OAServer oaServer;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {
            returnMap.put("result", oaServer.getLeave(arg0.get("objectId")));
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_QUERY_ERROR, "≤È—Ø ß∞‹");
        }
        return returnMap;
    }

    public void setOaServer(OAServer oaServer) {
        this.oaServer = oaServer;
    }
}
