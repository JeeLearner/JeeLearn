package com.learn.common.jdbc.jpa.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Persistable;

/**
 * 抽象实体基类，如果主键是数据库端自动生成 请使用{@link BaseEntity}，如果是Oracle 请使用{@link BaseOracleEntity}
 * @author JeeLearner
 * @date 2018年3月6日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public abstract class AbstractEntity<ID extends Serializable> implements Persistable<ID> {

    public abstract ID getId();

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    public abstract void setId(final ID id);

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Persistable#isNew()

     */
    public boolean isNew() {

        return null == getId();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        AbstractEntity<?> that = (AbstractEntity<?>) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}