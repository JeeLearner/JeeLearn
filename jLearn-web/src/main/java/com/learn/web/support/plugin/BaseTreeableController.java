package com.learn.web.support.plugin;

import java.io.Serializable;

import com.learn.common.jdbc.jpa.entity.BaseEntity;
import com.learn.common.plugin.entity.Treeable;
import com.learn.common.plugin.service.BaseTreeableService;
import com.learn.web.support.BaseController;

public abstract class BaseTreeableController<M extends BaseEntity<ID> & Treeable<ID>, ID extends Serializable> extends BaseController<M, ID> {

	protected BaseTreeableService<M, ID> baseService;

    //protected PermissionList permissionList = null;
    
}
