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
}
