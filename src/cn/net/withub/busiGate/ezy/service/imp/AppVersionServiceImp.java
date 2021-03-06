package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.dao.JdbcTool;
import cn.net.withub.util.exception.AppException;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppVersionServiceImp implements BusiGateService {

    private JdbcTool jdbcTool;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            List<Map<String, Object>> list = jdbcTool.queryForList(
                    "SELECT std_appversion.VERSION as version, std_appversion.DESCRIPTION as description,\n" +
                            "std_appversion.APKURL as apkUrl FROM std_appversion\n" +
                            "WHERE std_appversion.STATUS = 1"
            );

            // 若无符合的数据，则返回

            if (list.size() == 0) {
                returnMap.put("result", "{\"version\":\"\",\"description\":\"\",\"apkUrl\":\"\"}");
            } else {
                returnMap.put("result", JSON.toJSON(list.get(0)).toString());
            }
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