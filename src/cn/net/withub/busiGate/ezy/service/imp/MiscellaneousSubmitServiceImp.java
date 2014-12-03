package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import com.withub.common.util.StringUtil;
import com.withub.model.oa.po.Miscellaneous;
import com.withub.model.system.po.User;
import com.withub.service.oa.OaAppService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 提交综合事项
 */
public class MiscellaneousSubmitServiceImp implements BusiGateService {

    @Autowired
    private OaAppService oaAppService;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            String description = arg0.get("description");
            String approver = arg0.get("approver");

            Miscellaneous miscellaneous = new Miscellaneous();
            miscellaneous.setObjectId(arg0.get("objectId"));
            miscellaneous.setDescription(description);
            miscellaneous.setCurrentUser(new User());
            miscellaneous.getCurrentUser().setObjectId(arg1.getUserId());
            if (StringUtil.isNotEmpty(approver)) {
                miscellaneous.setApprover(new User());
                miscellaneous.getApprover().setObjectId(approver);
            }

            oaAppService.submitMiscellaneous(miscellaneous);

            returnMap.put("message", "1");
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_PROCESS_ERROR, "操作失败");
        }
        return returnMap;
    }

    public void setOaAppService(OaAppService oaAppService) {
        this.oaAppService = oaAppService;
    }
}
