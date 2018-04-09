package com.learn.modules.showcase.excel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.learn.common.jdbc.jpa.entity.BaseEntity;

/**
 * excel导出实体
 * @author JeeLearner
 * @date 2018年4月4日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
@Entity
@Table(name = "showcase_excel_data")
public class ExcelData extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2677227633707575925L;
	
	@Column(name = "content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}
