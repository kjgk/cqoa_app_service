package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import com.withub.common.util.DateUtil;
import com.withub.model.oa.po.CarUse;
import com.withub.model.system.po.User;
import com.withub.server.OAServer;

import java.util.HashMap;
import java.util.Map;

/**
 * 提交用车申请
 */
public class CarUseSubmitServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    private OAServer oaServer;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            String beginTime = arg0.get("beginTime");
            String endTime = arg0.get("endTime");
            String localCity = arg0.get("localCity");
            String description = arg0.get("description");
            String address = arg0.get("address");

            CarUse carUse = new CarUse();

            carUse.setBeginTime(DateUtil.convertStringToDate(beginTime, DateUtil.STANDARD_DATEMINUTE_FORMAT));    // yyyy-MM-dd HH:mm
            carUse.setEndTime(DateUtil.convertStringToDate(endTime, DateUtil.STANDARD_DATEMINUTE_FORMAT));
            carUse.setLocalCity(Integer.parseInt(localCity));
            carUse.setDescription(description);
            carUse.setAddress(address);

            carUse.setCurrentUser(new User());
            carUse.getCurrentUser().setObjectId(arg1.getUserId());

            oaServer.submitCarUse(carUse);

            returnMap.put("message", "1");
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_PROCESS_ERROR, "操作失败");
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
