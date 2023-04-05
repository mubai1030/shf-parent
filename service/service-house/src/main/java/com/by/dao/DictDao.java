package com.by.dao;

import com.by.base.BaseDao;
import com.by.entity.Dict;

import java.util.List;

/**
 * @author xiaobai
 * @create 2023-04-02 0:02
 */
public interface DictDao extends BaseDao<Dict> {
    List<Dict> findListByParentId(Long parentId);
    Integer countIsParent(Long id);
    String getNameById(Long id);
    Dict getByDictCode(String dictCode);
}
