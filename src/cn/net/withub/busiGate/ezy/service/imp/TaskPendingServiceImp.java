package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import com.withub.model.entity.query.RecordsetInfo;
import com.withub.server.OAServer;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * ��ѯ��������
 */
public class TaskPendingServiceImp implements BusiGateService {

    private OAServer oaServer;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {
            Integer currentPage = Integer.parseInt(arg0.get("currentPage"));
            Integer pageSize = Integer.parseInt(arg0.get("pageSize"));
            currentPage = currentPage < 0 ? 1 : currentPage;
            String flowType = arg0.get("flowType");
            RecordsetInfo recordsetInfo = oaServer.queryTask(arg1.getUserId(), flowType, "Running", currentPage, pageSize);
            returnMap.put("count", recordsetInfo.getTotalRecordCount().toString());
            returnMap.put("result", JSON.toJSON(recordsetInfo.getEntityList()).toString());
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_QUERY_ERROR, "��ѯʧ��");
        }
        return returnMap;
    }

    public void setOaServer(OAServer oaServer) {
        this.oaServer = oaServer;
    }
}
