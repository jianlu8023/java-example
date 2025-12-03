package com.github.jianlu8023.example.integration.middleware.jmreport.impl;

import com.alibaba.fastjson.*;
import lombok.extern.slf4j.*;
import org.jeecg.modules.drag.service.*;
import org.jeecg.modules.drag.vo.*;
import org.jeecg.modules.jmreport.common.util.*;
import org.jeecg.modules.jmreport.common.vo.*;
import org.jeecg.modules.jmreport.desreport.service.*;
import org.springframework.beans.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import javax.annotation.*;
import java.util.*;


@Slf4j
@Service
public class JimuDragExternalServiceImpl implements IOnlDragExternalService {

    @Resource
    @Lazy
    private IJimuReportDictService reportDictService;

    /**
     * 根据多个字典code查询多个字典项
     *
     * @param codeList
     * @return key = dictCode ； value=对应的字典项
     */
    @Override
    public Map<String, List<DragDictModel>> getManyDictItems(List<String> codeList, List<JSONObject> tableDictList) {
        Map<String, List<DragDictModel>> manyDragDictItems = new HashMap<>();
        if (!CollectionUtils.isEmpty(codeList)) {
            Map<String, List<JmDictModel>> dictItemsMap = reportDictService.getManyDictItems(codeList);
            dictItemsMap.forEach((k, v) -> {
                List<DragDictModel> dictItems = new ArrayList<>();
                v.forEach(dictItem -> {
                    DragDictModel dictModel = new DragDictModel();
                    BeanUtils.copyProperties(dictItem, dictModel);
                    dictItems.add(dictModel);
                });
                manyDragDictItems.put(k, dictItems);
            });
        }
        if (!CollectionUtils.isEmpty(tableDictList)) {
            tableDictList.forEach(item -> {
                List<DragDictModel> dictItems = new ArrayList<>();
                JSONObject object = JSONObject.parseObject(item.toString());
                String dictField = object.getString("dictField");
                String dictTable = object.getString("dictTable");
                String dictText = object.getString("dictText");
                String fieldName = object.getString("fieldName");
                List<JmDictModel> dictItemsList = reportDictService.queryTableDictItemsByCode(dictTable, dictText, dictField);
                dictItemsList.forEach(dictItem -> {
                    DragDictModel dictModel = new DragDictModel();
                    BeanUtils.copyProperties(dictItem, dictModel);
                    dictItems.add(dictModel);
                });
                manyDragDictItems.put(fieldName, dictItems);
            });
        }
        return manyDragDictItems;
    }

    /**
     * @param dictCode
     * @return
     */
    @Override
    public List<DragDictModel> getDictItems(String dictCode) {
        List<DragDictModel> dictItems = new ArrayList<>();
        if (OkConvertUtils.isNotEmpty(dictCode)) {
            List<JmDictModel> dictItemsList = reportDictService.queryDictItemsByCode(dictCode);
            dictItemsList.forEach(dictItem -> {
                DragDictModel dictModel = new DragDictModel();
                BeanUtils.copyProperties(dictItem, dictModel);
                dictItems.add(dictModel);
            });
        }
        return dictItems;
    }

    /**
     * 添加日志
     *
     * @param dragLogDTO
     */
    @Override
    public void addLog(DragLogDTO dragLogDTO) {

    }

    /**
     * 保存日志
     *
     * @param logMsg
     * @param logType
     * @param operateType
     */
    @Override
    public void addLog(String logMsg, int logType, int operateType) {

    }
}
