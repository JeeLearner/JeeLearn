package com.learn.common.exception.maintain.notification;

/**
 * 
 * @author JeeLearner
 * @date 2018年4月9日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class TemplateNotFoundException extends TemplateException {
    public TemplateNotFoundException(String templateName) {
        super("notification.template.not.found", new Object[] {templateName});
    }
}
