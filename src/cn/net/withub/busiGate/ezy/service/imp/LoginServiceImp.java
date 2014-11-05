package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.ezy.model.User;
import cn.net.withub.busiGate.ezy.util.Md5Util;
import cn.net.withub.busiGate.loginInfo.LoginHelper;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import net.sf.json.JSONSerializer;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> resultMap = new HashMap<String, String>();
        User user = new User();
        try {
            List list = jdbcTool.queryForList("select a.objectId objectId, a.name username, b.name account, b.salt, b.password" +
                    " from sys_user a, sys_account b where a.objectId = b.userId and b.name = ? "
                    , new Object[]{arg0.get("username")});

            String message = "0";
            if (!CollectionUtils.isEmpty(list)) {
                Map item = (Map) list.get(0);
                String passwordMD5 = Md5Util.getStringMD5(item.get("salt") + arg0.get("password"));
                if (passwordMD5.equalsIgnoreCase((String) item.get("password"))) {
                    user.setUserId((String) item.get("objectId"));
                    user.setUsername((String) item.get("username"));
                    user.setLoginName((String) item.get("account"));
                    message = "1";
                    String ticket = LoginHelper.login(user);
                    resultMap.put("ticket", ticket);
                }
            }
            resultMap.put("message", message);
            resultMap.put("userInfo", JSONSerializer.toJSON(user).toString());
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_LOGIN_ERROR, "µÇÂ¼Ê§°Ü");
        }
        return resultMap;
    }

    public void setJdbcTool(JdbcTool jdbcTool) {
        this.jdbcTool = jdbcTool;
    }
}
