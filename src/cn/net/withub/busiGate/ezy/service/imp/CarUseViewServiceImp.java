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

public class CarUseViewServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            List<Map<String, Object>> list = jdbcTool.queryForList(
                  "SELECT sys_user.name AS proposerName, sys_organization.name AS organizationName,\n" +
                          "oa_caruse.DESCRIPTION as description,\n" +
                          "oa_caruse.BEGINTIME as beginTime, oa_caruse.ENDTIME as endTime,\n" +
                          "oa_caruse.LOCALCITY as localCity, oa_caruse.ADDRESS as address,\n" +
                          "oa_caruse.CREATETIME as createTime\n" +
                          "FROM oa_caruse, sys_user, sys_organization\n" +
                          "WHERE 1=1\n" +
                          "AND oa_caruse.OBJECTID = ?\n" +
                          "AND sys_user.OBJECTID = oa_caruse.PROPOSER\n" +
                          "AND sys_organization.OBJECTID = sys_user.ORGANIZATIONID"
                    , new Object[]{arg0.get("objectId")});

            returnMap.put("result", JSONSerializer.toJSON(list.get(0)).toString());
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_QUERY_ERROR, "��ѯʧ��");
        }
        return returnMap;
    }

    public void setJdbcTool(JdbcTool jdbcTool) {
        this.jdbcTool = jdbcTool;
    }
}