package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import net.sf.json.JSONSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看流程信息
 */
public class InstanceViewServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {
            List<Map<String, Object>> list = jdbcTool.queryForList("select a.name, f.name flowNodeName, b.name flowTypeName, c.name creator, d.name organization" +
                    " , a.createTime createTime, a.finishTime finishTime, e.name statusName" +
                    " from wf_instance a, wf_flowtype b, sys_user c, sys_organization d, sys_code e, wf_flownode f" +
                    " where a.objectId = ?" +
                    " and a.flowTypeId = b.objectId and a.creator = c.objectId and a.organizationId = d.objectId and a.status = e.objectId and a.currentFlowNodeId = f.objectId"
                    , new Object[]{arg0.get("objectId")});

            Map result = list.get(0);

            if (result.get("finishTime") == null) {
                // todo
                result.put("flowNodeName", null);
                result.put("handler", null);
            } else {
                result.put("flowNodeName", null);
                result.put("handler", null);
            }

            returnMap.put("result", JSONSerializer.toJSON(result).toString());
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_QUERY_ERROR, "查询失败");
        }
        return returnMap;
    }

    public void setJdbcTool(JdbcTool jdbcTool) {
        this.jdbcTool = jdbcTool;
    }
}
