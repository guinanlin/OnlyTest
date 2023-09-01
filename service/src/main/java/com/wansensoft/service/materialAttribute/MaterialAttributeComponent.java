package com.wansensoft.service.materialAttribute;

import com.alibaba.fastjson.JSONObject;
import com.wansensoft.service.ICommonQuery;
import com.wansensoft.utils.Constants;
import com.wansensoft.utils.QueryUtils;
import com.wansensoft.utils.StringUtil;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
@MaterialAttributeResource
public class MaterialAttributeComponent implements ICommonQuery {

    private final MaterialAttributeService materialAttributeService;

    public MaterialAttributeComponent(MaterialAttributeService materialAttributeService) {
        this.materialAttributeService = materialAttributeService;
    }

    @Override
    public Object selectOne(Long id) throws Exception {
        return materialAttributeService.getMaterialAttribute(id);
    }

    @Override
    public List<?> select(Map<String, String> map)throws Exception {
        return getMaterialList(map);
    }

    private List<?> getMaterialList(Map<String, String> map) throws Exception{
        String search = map.get(Constants.SEARCH);
        String attributeName = StringUtil.getInfo(search, "attributeName");
        return materialAttributeService.select(attributeName, QueryUtils.offset(map), QueryUtils.rows(map));
    }

    @Override
    public Long counts(Map<String, String> map)throws Exception {
        String search = map.get(Constants.SEARCH);
        String attributeField = StringUtil.getInfo(search, "attributeField");
        return materialAttributeService.countMaterialAttribute(attributeField);
    }

    @Override
    public int insert(JSONObject obj, HttpServletRequest request) throws Exception{
        return materialAttributeService.insertMaterialAttribute(obj, request);
    }

    @Override
    public int update(JSONObject obj, HttpServletRequest request)throws Exception {
        return materialAttributeService.updateMaterialAttribute(obj, request);
    }

    @Override
    public int delete(Long id, HttpServletRequest request)throws Exception {
        return materialAttributeService.deleteMaterialAttribute(id, request);
    }

    @Override
    public int deleteBatch(String ids, HttpServletRequest request)throws Exception {
        return materialAttributeService.batchDeleteMaterialAttribute(ids, request);
    }

    @Override
    public int checkIsNameExist(Long id, String name)throws Exception {
        return materialAttributeService.checkIsNameExist(id, name);
    }

}