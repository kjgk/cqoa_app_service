package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import com.withub.common.util.StringUtil;
import net.sf.json.JSONSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询经办表单（本人提交的和本人审批过的表单）
 */
public class InstanceListServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {
            Integer currentPage = Integer.parseInt(arg0.get("currentPage"));
            Integer pageSize = Integer.parseInt(arg0.get("pageSize"));
            currentPage = currentPage < 0 ? 1 : currentPage;
            String flowType = arg0.get("flowType");
            String complate = arg0.get("complate");

            String sql = " from wf_instance a\n" +
                    "  , (\n" +
                    "      select\n" +
                    "        c.instanceid,\n" +
                    "        max(a.finishtime) finishtime\n" +
                    "      from wf_task a, wf_mastertask b, wf_subinstance c, wf_flowtype d, wf_flownode e\n" +
                    "      where a.mastertaskid = b.objectid and b.subinstanceid = c.objectid \n" +
                    "            and b.flownodeid = e.objectid and d.objectid = e.flowtypeid\n" +
                    "        and a.owner = ?\n" +
                    "        and d.flowtypetag = ?\n" +
                    "        and a.result is not null\n" +
                    "      group by c.instanceid\n" +
                    "    ) b\n" +
                    "where a.objectid = b.instanceid";
            if (StringUtil.isNotEmpty(complate) && !StringUtil.compareValue("0", complate)) {
                sql += " and a.result = '69F248C7-30CC-4723-A100-3DECD577FCDD'";
            }

            List list = jdbcTool.queryForList("select a.objectid objectId, a.instanceName, b.finishtime finishTime " +
                    sql + " order by b.finishtime desc limit ?, ?"
                    , new Object[]{arg1.getUserId(), flowType, (currentPage - 1) * pageSize, pageSize});
            Long count = jdbcTool.queryForLong("select count(1) " + sql, new Object[]{arg1.getUserId(), flowType});

            List items = new ArrayList();
            for (Map map : (List<Map>) list) {
                items.add(map);
            }
            returnMap.put("count", count.toString());
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
