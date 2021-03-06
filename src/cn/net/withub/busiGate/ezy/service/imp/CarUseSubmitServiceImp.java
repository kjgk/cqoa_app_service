package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import com.withub.common.util.DateUtil;
import com.withub.common.util.StringUtil;
import com.withub.model.oa.po.CarUse;
import com.withub.model.oa.po.CarUseUser;
import com.withub.model.system.po.User;
import com.withub.service.oa.OaAppService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 提交用车申请
 */
public class CarUseSubmitServiceImp implements BusiGateService {

    private OaAppService oaAppService;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            String users = arg0.get("users");
            String beginTime = arg0.get("beginTime");
            String endTime = arg0.get("endTime");
            String region = arg0.get("region");
            String description = arg0.get("description");
            String address = arg0.get("address");
            String approver = arg0.get("approver");

            CarUse carUse = new CarUse();

            if (StringUtil.isNotEmpty(users)) {
                carUse.setCarUseUserList(new ArrayList<CarUseUser>());
                for (String userId : users.split(",")) {
                    CarUseUser carUseUser = new CarUseUser();
                    carUseUser.setUser(new User());
                    carUseUser.getUser().setObjectId(userId);
                    carUse.getCarUseUserList().add(carUseUser);
                }
            }

            carUse.setObjectId(arg0.get("objectId"));
            carUse.setBeginTime(DateUtil.convertStringToDate(beginTime, DateUtil.STANDARD_DATEMINUTE_FORMAT));    // yyyy-MM-dd HH:mm
            carUse.setEndTime(DateUtil.convertStringToDate(endTime, DateUtil.STANDARD_DATEMINUTE_FORMAT));
            carUse.setRegion(Integer.parseInt(region));
            carUse.setDescription(description);
            carUse.setAddress(address);
            carUse.setCurrentUser(new User());
            carUse.getCurrentUser().setObjectId(arg1.getUserId());
            if (StringUtil.isNotEmpty(approver)) {
                carUse.setApprover(new User());
                carUse.getApprover().setObjectId(approver);
            }

            oaAppService.submitCarUse(carUse);

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
