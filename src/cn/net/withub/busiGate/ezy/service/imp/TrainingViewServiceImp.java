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

public class TrainingViewServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            List<Map<String, Object>> list = jdbcTool.queryForList(
                   "SELECT sys_user.NAME AS proposerName,sys_organization.NAME AS organizationName,\n" +
                           "oa_training.CREATETIME AS createTime, oa_training.BEGINDATE AS beginDate, \n" +
                           "oa_training.ENDDATE AS endDate,oa_training.ADDRESS AS address,\n" +
                           "oa_training.CONTENT AS content,oa_training.TARGET AS target,\n" +
                           "oa_training.PEOPLECOUNT AS peopleCount,oa_training.PUBLICITY as publicity\n" +
                           "FROM oa_training, sys_user, sys_organization\n" +
                           "WHERE 1=1\n" +
                           "AND oa_training.OBJECTID = ?\n" +
                           "AND sys_user.OBJECTID = oa_training.PROPOSER\n" +
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
