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
 * 查看流程信息
 */
public class InstanceViewServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            List list = jdbcTool.queryForList("select * from wf_instance a where objectId = ?"
                    , new Object[]{arg0.get("objectId")});

            returnMap.put("result", JSONSerializer.toJSON(list.get(0)).toString());
        } catch (Exception e1) {
            throw new AppException(EzyErrorCode.EZY_QUERY_ERROR, "查询失败");
        }
        return returnMap;
    }

    public void setJdbcTool(JdbcTool jdbcTool) {
        this.jdbcTool = jdbcTool;
    }
}
