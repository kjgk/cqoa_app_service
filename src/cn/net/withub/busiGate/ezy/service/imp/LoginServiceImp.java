package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.ezy.model.User;
import cn.net.withub.busiGate.loginInfo.LoginHelper;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import com.withub.service.oa.OaAppService;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class LoginServiceImp implements BusiGateService {

    private OaAppService oaAppService;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> resultMap = new HashMap<String, String>();

        try {
            com.withub.model.system.po.User result = oaAppService.login(arg0.get("username"), arg0.get("password"));
            String message = "0";
            if (result != null) {
                User user = new User();
                user.setUserId(result.getObjectId());
                user.setUsername(result.getName());
                user.setRoleTag(result.getRole().getRoleTag());
                user.setOrganizationCode(result.getOrganization().getCode());
                user.setLoginName(arg0.get("username"));
                user.setLoginBusiType("");
                user.setFydm("");
                message = "1";
                String ticket = LoginHelper.login(user);
                resultMap.put("ticket", ticket);
                resultMap.put("userInfo", JSON.toJSON(user).toString());
            }
            resultMap.put("message", message);
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_LOGIN_ERROR, "登录失败");
        }
        return resultMap;
    }

    public void setOaAppService(OaAppService oaAppService) {
        this.oaAppService = oaAppService;
    }
}
