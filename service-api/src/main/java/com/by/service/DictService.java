package com.by.service;

import com.by.base.BaseService;
import com.by.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * @author xiaobai
 * @create 2023-04-01 23:58
 */
public interface DictService extends BaseService<Dict> {
    List<Map<String,Object>> findZnodes(Long id);

    /**
     * 根据 上级id 获取子节点数据列表
     * @param parentId
     * @return
     */
    List<Dict> findListByParentId(Long parentId);

    /**
     * 根据 编码 获取子节点数据列表
     * @param dictCode
     * @return
     */
    List<Dict> findListByDictCode(String dictCode);

    /*通过字典id，获取字典名称*/
    String getNameById(Long id);
}
