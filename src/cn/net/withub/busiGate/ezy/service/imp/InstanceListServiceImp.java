package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import com.withub.service.oa.OaAppService;

import java.util.HashMap;
import java.util.Map;

/**
 * ��ѯ������������ύ�ĺͱ����������ı���
 */
public class InstanceListServiceImp implements BusiGateService {

    private OaAppService oaAppService;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {
            Integer currentPage = Integer.parseInt(arg0.get("currentPage"));
            Integer pageSize = Integer.parseInt(arg0.get("pageSize"));
            currentPage = currentPage < 0 ? 1 : currentPage;
            String flowType = arg0.get("flowType");
            String complete = arg0.get("complete");

            returnMap = oaAppService.listInstance(arg1.getUserId(), flowType, complete, currentPage, pageSize);

        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_QUERY_ERROR, "��ѯʧ��");
        }
        return returnMap;
    }

    public void setOaAppService(OaAppService oaAppService) {
        this.oaAppService = oaAppService;
    }
}
