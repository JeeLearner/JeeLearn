package com.learn.common.jdbc.jpa.entity.search.filter;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * or 条件
 * @author lyd
 * @date 2018年3月5日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class OrCondition implements SearchFilter {

    private List<SearchFilter> orFilters = Lists.newArrayList();

    OrCondition() {
    }

    public OrCondition add(SearchFilter filter) {
        this.orFilters.add(filter);
        return this;
    }

    public List<SearchFilter> getOrFilters() {
        return orFilters;
    }

    @Override
    public String toString() {
        return "OrCondition{" + orFilters + '}';
    }
}
