package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import com.withub.common.util.DateUtil;
import com.withub.common.util.StringUtil;
import com.withub.model.oa.po.Outgoing;
import com.withub.model.oa.po.OutgoingUser;
import com.withub.model.system.po.Code;
import com.withub.model.system.po.User;
import com.withub.service.oa.OaAppService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 提交外出申请
 */
public class OutgoingSubmitServiceImp implements BusiGateService {

    @Autowired
    private OaAppService oaAppService;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            String users = arg0.get("users");
            String beginDate = arg0.get("beginDate");
            String endDate = arg0.get("endDate");
            String localCity = arg0.get("localCity");
            String description = arg0.get("description");
            String destination = arg0.get("destination");
            String driveRoute = arg0.get("driveRoute");
            String transportation = arg0.get("transportation");
            String requiredCar = arg0.get("requiredCar");
            String approver = arg0.get("approver");

            Outgoing outgoing = new Outgoing();
            if (StringUtil.isNotEmpty(users)) {
                outgoing.setOutgoingUserList(new ArrayList<OutgoingUser>());
                for (String userId : users.split(",")) {
                    OutgoingUser outgoingUser = new OutgoingUser();
                    outgoingUser.setUser(new User());
                    outgoingUser.getUser().setObjectId(userId);
                    outgoing.getOutgoingUserList().add(outgoingUser);
                }
            }
            outgoing.setObjectId(arg0.get("objectId"));
            outgoing.setBeginDate(DateUtil.convertStringToDate(beginDate, DateUtil.STANDARD_DATE_FORMAT));    // yyyy-MM-dd
            outgoing.setEndDate(DateUtil.convertStringToDate(endDate, DateUtil.STANDARD_DATE_FORMAT));
            outgoing.setLocalCity(Integer.parseInt(localCity));
            outgoing.setDescription(description);
            outgoing.setDestination(destination);
            outgoing.setDriveRoute(driveRoute);
            outgoing.setTransportation(new Code());
            outgoing.getTransportation().setObjectId(transportation);
            outgoing.setRequiredCar(Integer.parseInt(requiredCar));
            outgoing.setCurrentUser(new User());
            outgoing.getCurrentUser().setObjectId(arg1.getUserId());
            if (StringUtil.isNotEmpty(approver)) {
                outgoing.setApprover(new User());
                outgoing.getApprover().setObjectId(approver);
            }

            oaAppService.submitOutgoing(outgoing);

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
