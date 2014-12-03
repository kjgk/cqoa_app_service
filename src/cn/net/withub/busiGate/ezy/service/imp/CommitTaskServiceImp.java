package cn.net.withub.busiGate.ezy.service.imp;

import cn.net.withub.busiGate.ezy.exceptin.EzyErrorCode;
import cn.net.withub.busiGate.loginInfo.LoginInfo;
import cn.net.withub.busiGate.service.BusiGateService;
import cn.net.withub.util.exception.AppException;
import com.withub.common.util.StringUtil;
import com.withub.model.workflow.po.TaskContext;
import com.withub.service.oa.OaAppService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理任务
 */
public class CommitTaskServiceImp implements BusiGateService {

    @Autowired
    private OaAppService oaAppService;

    public Map<String, String> busi(Map<String, String> arg0, LoginInfo arg1)
            throws AppException {

        Map<String, String> returnMap = new HashMap<String, String>();
        try {

            String taskId = arg0.get("taskId");
            String opinion = arg0.get("opinion");
            String handleResult = arg0.get("handleResult");
            String approvers = arg0.get("approvers");
            String nextFlowNode = arg0.get("nextFlowNode");
            List<String> approverList = new ArrayList<String>();
            if (StringUtils.isNotEmpty(approvers)) {
                for (String approver : approvers.split(",")) {
                    approverList.add(approver);
                }
            }
            List<TaskContext> taskContextList = null;
            if (StringUtil.isNotEmpty(nextFlowNode)) {
                TaskContext taskContext = new TaskContext();
                taskContext.setContextKey("nextFlowNode");
                taskContext.setContextValue(nextFlowNode);
                taskContextList.add(taskContext);
            }
            oaAppService.commitTask(arg1.getUserId(), taskId, taskContextList, handleResult, opinion, approverList);

            returnMap.put("message", "1");
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new AppException(EzyErrorCode.EZY_PROCESS_ERROR, "操作失败");
        }
        return returnMap;
    }

    public void setOaAppService(OaAppService oaAppService) {
        this.oaAppService = oaAppService;
    }
}
