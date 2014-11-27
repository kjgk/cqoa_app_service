package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import com.withub.common.util.DateUtil;
import com.withub.model.oa.po.Leave;
import com.withub.model.system.po.User;
import com.withub.service.oa.OaAppService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Ã·Ωª«ÎºŸ…Í«Î
 */
public class LeaveSubmitServiceImp implements BusiGateService {

    @Autowired
    private OaAppService oaAppService;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            String beginDate = arg0.get("beginDate");
            String endDate = arg0.get("endDate");
            String description = arg0.get("description");

            Leave leave = new Leave();
            leave.setObjectId(arg0.get("objectId"));
            leave.setBeginDate(DateUtil.convertStringToDate(beginDate, DateUtil.STANDARD_DATE_FORMAT));    // yyyy-MM-dd
            leave.setEndDate(DateUtil.convertStringToDate(endDate, DateUtil.STANDARD_DATE_FORMAT));
            leave.setDescription(description);
            leave.setCurrentUser(new User());
            leave.getCurrentUser().setObjectId(arg1.getUserId());

            oaAppService.submitLeave(leave);

            returnMap.put("message", "1");
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_PROCESS_ERROR, "≤Ÿ◊˜ ß∞‹");
        }
        return returnMap;
    }

    public void setOaAppService(OaAppService oaAppService) {
        this.oaAppService = oaAppService;
    }
}
