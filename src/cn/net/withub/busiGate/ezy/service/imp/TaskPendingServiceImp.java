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
 * ��ѯ��������
 */
public class TaskPendingServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {
            Integer currentPage = Integer.parseInt(arg0.get("currentPage"));
            Integer pageSize = Integer.parseInt(arg0.get("pageSize"));
            currentPage = currentPage < 0 ? 1: currentPage;

            String sql = "from vw_taskinfo a where handler = ?";

            List list = jdbcTool.queryForList("select * " + sql + " order by taskCreateTime desc limit ?, ?"
                    , new Object[]{arg1.getUserId(), (currentPage - 1) * pageSize, pageSize});
            Long count = jdbcTool.queryForLong("select count(1) " + sql, new Object[]{arg1.getUserId()});

            List items = new ArrayList();
            for (Map map : (List<Map>) list) {
                items.add(map);
            }
            returnMap.put("count", count.toString());
            returnMap.put("resultMap", JSONSerializer.toJSON(items).toString());
        } catch (Exception e1) {
            throw new AppException(EzyErrorCode.EZY_QUERY_ERROR, "��ѯʧ��");
        }
        return returnMap;
    }

    public void setJdbcTool(JdbcTool jdbcTool) {
        this.jdbcTool = jdbcTool;
    }
}
