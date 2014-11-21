package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import net.sf.json.JSONSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询审批记录
 */
public class InstanceTaskLogServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            String sql = "from vw_instancetasklog a where a.instanceId = ?";

            List list = jdbcTool.queryForList("select a.flowNodeName flowNodeName, a.handlerName handlerName " +
                    ", a.taskHandleResultName taskHandleResultName, a.opinion opinion, a.taskFinishTime taskFinishTime " +
                    sql + " order by taskTimeMillis"
                    , new Object[]{arg0.get("instanceId")});

            List items = new ArrayList();
            for (Map map : (List<Map>) list) {
                items.add(map);
            }
            returnMap.put("result", JSONSerializer.toJSON(items).toString());
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
