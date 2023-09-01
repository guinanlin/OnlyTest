package com.wansensoft.service.log;

import com.alibaba.fastjson.JSONObject;
import com.wansensoft.service.ICommonQuery;
import com.wansensoft.utils.Constants;
import com.wansensoft.utils.QueryUtils;
import com.wansensoft.utils.StringUtil;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service(value = "log_component")
@LogResource
public class LogComponent implements ICommonQuery {

    private final LogServiceImpl logServiceImpl;

    public LogComponent(LogServiceImpl logServiceImpl) {
        this.logServiceImpl = logServiceImpl;
    }

    @Override
    public Object selectOne(Long id) throws Exception {
        return logServiceImpl.getLog(id);
    }

    @Override
    public List<?> select(Map<String, String> map)throws Exception {
        return getLogList(map);
    }

    private List<?> getLogList(Map<String, String> map)throws Exception {
        String search = map.get(Constants.SEARCH);
        String operation = StringUtil.getInfo(search, "operation");
        String userInfo = StringUtil.getInfo(search, "userInfo");
        String clientIp = StringUtil.getInfo(search, "clientIp");
        Integer status = StringUtil.parseInteger(StringUtil.getInfo(search, "status"));
        String beginTime = StringUtil.getInfo(search, "beginTime");
        String endTime = StringUtil.getInfo(search, "endTime");
        String content = StringUtil.getInfo(search, "content");
        return logServiceImpl.select(operation, userInfo, clientIp, status, beginTime, endTime, content,
                QueryUtils.offset(map), QueryUtils.rows(map));
    }

    @Override
    public Long counts(Map<String, String> map)throws Exception {
        String search = map.get(Constants.SEARCH);
        String operation = StringUtil.getInfo(search, "operation");
        String userInfo = StringUtil.getInfo(search, "userInfo");
        String clientIp = StringUtil.getInfo(search, "clientIp");
        Integer status = StringUtil.parseInteger(StringUtil.getInfo(search, "status"));
        String beginTime = StringUtil.getInfo(search, "beginTime");
        String endTime = StringUtil.getInfo(search, "endTime");
        String content = StringUtil.getInfo(search, "content");
        return logServiceImpl.countLog(operation, userInfo, clientIp, status, beginTime, endTime, content);
    }

    @Override
    public int insert(JSONObject obj, HttpServletRequest request)throws Exception {
        return logServiceImpl.insertLog(obj, request);
    }

    @Override
    public int update(JSONObject obj, HttpServletRequest request)throws Exception {
        return logServiceImpl.updateLog(obj, request);
    }

    @Override
    public int delete(Long id, HttpServletRequest request)throws Exception {
        return logServiceImpl.deleteLog(id, request);
    }

    @Override
    public int deleteBatch(String ids, HttpServletRequest request)throws Exception {
        return logServiceImpl.batchDeleteLog(ids, request);
    }

    @Override
    public int checkIsNameExist(Long id, String name)throws Exception {
        return 0;
    }

}