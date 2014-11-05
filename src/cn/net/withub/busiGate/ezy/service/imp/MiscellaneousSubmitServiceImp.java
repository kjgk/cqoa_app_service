package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import com.withub.model.oa.po.Miscellaneous;
import com.withub.model.system.po.User;
import com.withub.server.OAServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * �ύ�ۺ�����
 */
public class MiscellaneousSubmitServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    @Autowired
    private OAServer oaServer;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            String description = arg0.get("description");
            Miscellaneous miscellaneous = new Miscellaneous();
            miscellaneous.setDescription(description);
            miscellaneous.setCurrentUser(new User());
            miscellaneous.getCurrentUser().setObjectId(arg1.getUserId());

            oaServer.submitMiscellaneous(miscellaneous);

            returnMap.put("message", "1");
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_PROCESS_ERROR, "����ʧ��");
        }
        return returnMap;
    }

    public void setJdbcTool(JdbcTool jdbcTool) {
        this.jdbcTool = jdbcTool;
    }
}
