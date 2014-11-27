package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import com.withub.common.util.DateUtil;
import com.withub.model.oa.po.Training;
import com.withub.model.system.po.User;
import com.withub.service.oa.OaAppService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * �ύ��ѵ����
 */
public class TrainingSubmitServiceImp implements BusiGateService {

    @Autowired
    private OaAppService oaAppService;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            String beginDate = arg0.get("beginDate");
            String endDate = arg0.get("endDate");
            String address = arg0.get("address");
            String content = arg0.get("content");
            String peopleCount = arg0.get("peopleCount");
            String publicity = arg0.get("publicity");
            String target = arg0.get("target");

            Training training = new Training();
            training.setObjectId(arg0.get("objectId"));
            training.setBeginDate(DateUtil.convertStringToDate(beginDate, DateUtil.STANDARD_DATE_FORMAT));    // v
            training.setEndDate(DateUtil.convertStringToDate(endDate, DateUtil.STANDARD_DATE_FORMAT));
            training.setAddress(address);
            training.setContent(content);
            training.setPeopleCount(Integer.parseInt(peopleCount));
            training.setTarget(target);
            training.setPublicity(publicity);
            training.setCurrentUser(new User());
            training.getCurrentUser().setObjectId(arg1.getUserId());

            oaAppService.submitTraining(training);

            returnMap.put("message", "1");
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_PROCESS_ERROR, "����ʧ��");
        }
        return returnMap;
    }

    public void setOaAppService(OaAppService oaAppService) {
        this.oaAppService = oaAppService;
    }
}
