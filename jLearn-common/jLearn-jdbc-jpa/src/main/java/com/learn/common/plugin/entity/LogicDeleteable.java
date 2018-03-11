package com.learn.common.plugin.entity;

/**
 * 实体实现该接口表示想要逻辑删除
 * 
 * 为了简化开发 约定删除标识列名为deleted，如果想自定义删除的标识列名：
 * 1、使用注解元数据
 * 2、写一个 getColumn() 方法 返回列名
 * @author JeeLearner
 * @date 2018年3月5日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public interface LogicDeleteable {

    public Boolean getDeleted();

    public void setDeleted(Boolean deleted);

    /**
     * 标识为已删除
     */
    public void markDeleted();

}
