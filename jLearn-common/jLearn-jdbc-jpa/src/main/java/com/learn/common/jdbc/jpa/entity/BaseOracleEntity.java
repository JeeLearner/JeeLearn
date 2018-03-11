package com.learn.common.jdbc.jpa.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 抽象实体基类，提供统一的ID，和相关的基本功能方法
 *   如果是如mysql这种自动生成主键的，请参考{@link BaseEntity}
 *   子类只需要在类头上加 @SequenceGenerator(name="seq", sequenceName="你的sequence名字")
 * @author JeeLearner
 * @date 2018年3月6日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@MappedSuperclass
public abstract class BaseOracleEntity<PK extends Serializable> extends AbstractEntity<PK> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private PK id;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }
}