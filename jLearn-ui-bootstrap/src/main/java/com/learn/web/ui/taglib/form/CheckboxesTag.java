package com.learn.web.ui.taglib.form;

import org.springframework.web.servlet.support.BindStatus;

import com.learn.web.ui.taglib.form.bind.SearchBindStatus;

import javax.servlet.jsp.JspException;

/**
 * 取值时
 * 1、先取parameter
 * 2、如果找不到再找attribute (page--->request--->session--->application)
 * @author JeeLearner
 * @date 2018年3月12日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class CheckboxesTag extends org.springframework.web.servlet.tags.form.CheckboxesTag {

    private BindStatus bindStatus = null;

    @Override
    protected BindStatus getBindStatus() throws JspException {
        if (this.bindStatus == null) {
            this.bindStatus = SearchBindStatus.create(pageContext, getName(), getRequestContext(), false);
        }
        return this.bindStatus;
    }

    @Override
    protected String getPropertyPath() throws JspException {
        return getPath();
    }


    @Override
    public void doFinally() {
        super.doFinally();
        this.bindStatus = null;
    }

}
