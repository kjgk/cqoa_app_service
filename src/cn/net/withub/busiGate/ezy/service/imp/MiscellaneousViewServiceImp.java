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

public class MiscellaneousViewServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            List list = jdbcTool.queryForList(
                    "SELECT oa_miscellaneous.description, sys_user.name AS proposerName, sys_organization.name AS organizationName " +
                    "FROM oa_miscellaneous, sys_user,sys_organization " +
                    "WHERE 1=1 " +
                    "AND oa_miscellaneous.objectId = ? " +
                    "AND oa_miscellaneous.proposer = sys_user.objectId " +
                    "AND sys_user.organizationId = sys_organization.objectId"
                    , new Object[]{arg0.get("objectId")});

            returnMap.put("result", JSONSerializer.toJSON(list.get(0)).toString());
        } catch (Exception e1) {
            throw new AppException(EzyErrorCode.EZY_QUERY_ERROR, "≤È—Ø ß∞‹");
        }
        return returnMap;
    }

    public void setJdbcTool(JdbcTool jdbcTool) {
        this.jdbcTool = jdbcTool;
    }
}
