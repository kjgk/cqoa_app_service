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

public class OutgoingViewServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            List<Map<String, Object>> list = jdbcTool.queryForList(
                   "SELECT b.name AS proposerName, c.name AS organizationName,\n" +
                           "a.CREATETIME AS createTime, a.BEGINDATE as beginDate,\n" +
                           "a.ENDDATE as endDate, a.LOCALCITY as localCity,\n" +
                           "a.DESCRIPTION as description, a.DESTINATION as destination,\n" +
                           "a.DRIVEROUTE as driverRoute, a.TRANSPORTATION as transportationId, d.name as transportation,\n" +
                           "a.REQUIREDCAR as requiredCar\n" +
                           "FROM oa_outgoing a, sys_user b, sys_organization c, sys_code d\n" +
                           "WHERE 1=1\n" +
                           "AND a.OBJECTID = ?\n" +
                           "AND b.OBJECTID = a.PROPOSER\n" +
                           "AND c.OBJECTID = b.ORGANIZATIONID\n" +
                           "AND d.OBJECTID = a.TRANSPORTATION"
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
