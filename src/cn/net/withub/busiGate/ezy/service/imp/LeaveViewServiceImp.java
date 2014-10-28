package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import net.sf.json.JSONSerializer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaveViewServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            List<Map<String, Object>> list = jdbcTool.queryForList(
                  "SELECT sys_user.name AS proposerName, sys_organization.name AS organizationName,\n" +
                          "oa_leave.CREATETIME AS createTime, oa_leave.BEGINDATE AS beginDate,\n" +
                          "oa_leave.ENDDATE AS endDate, oa_leave.LOCALCITY as localCity,\n" +
                          "oa_leave.DESCRIPTION as description\n" +
                          "FROM oa_leave, sys_user, sys_organization\n" +
                          "WHERE 1=1\n" +
                          "AND oa_leave.OBJECTID = ?\n" +
                          "AND sys_user.OBJECTID = oa_leave.PROPOSER\n" +
                          "AND sys_organization.OBJECTID = sys_user.ORGANIZATIONID"
                    , new Object[]{arg0.get("objectId")});
            for (Map item : list) {
                item.put("beginDate", new Date(((java.sql.Date) item.get("beginDate")).getTime()));
                item.put("endDate", new Date(((java.sql.Date) item.get("endDate")).getTime()));
            }

            returnMap.put("result", JSONSerializer.toJSON(list.get(0)).toString());
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_QUERY_ERROR, "≤È—Ø ß∞‹");
        }
        return returnMap;
    }

    public void setJdbcTool(JdbcTool jdbcTool) {
        this.jdbcTool = jdbcTool;
    }
}
