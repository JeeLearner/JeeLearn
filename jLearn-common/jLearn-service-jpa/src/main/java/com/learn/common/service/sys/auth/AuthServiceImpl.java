package com.learn.common.service.sys.auth;

import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.common.service.sys.group.GroupService;
import com.learn.common.service.sys.user.UserService;
import com.learn.common.sys.auth.dao.AuthDao;
import com.learn.common.sys.auth.entity.Auth;
import com.learn.common.sys.group.entity.Group;
import com.learn.common.sys.user.entity.User;

@Service
public class AuthServiceImpl extends BaseService<Auth, Long> implements AuthService {

	@Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    private AuthDao getAuthDao() {
        return (AuthDao) baseRepository;
    }

    /**
     * 为用户添加角色
     * @author lyd
     * @date 2018年3月9日
     * @param userIds
     * @param m
     */
    @Override
    public void addUserAuth(Long[] userIds, Auth m) {

        if (ArrayUtils.isEmpty(userIds)) {
            return;
        }

        for (Long userId : userIds) {

            User user = userService.findOne(userId);
            if (user == null) {
                continue;
            }

            Auth auth = getAuthDao().findByUserId(userId);
            if (auth != null) {
                auth.addRoleIds(m.getRoleIds());
                continue;
            }
            auth = new Auth();
            auth.setUserId(userId);
            auth.setType(m.getType());
            auth.setRoleIds(m.getRoleIds());
            save(auth);
        }
    }

    /**
     * 为组添加角色
     * @author lyd
     * @date 2018年3月9日
     * @param groupIds
     * @param m
     */
    @Override
    public void addGroupAuth(Long[] groupIds, Auth m) {
        if (ArrayUtils.isEmpty(groupIds)) {
            return;
        }

        for (Long groupId : groupIds) {
            Group group = groupService.findOne(groupId);
            if (group == null) {
                continue;
            }

            Auth auth = getAuthDao().findByGroupId(groupId);
            if (auth != null) {
                auth.addRoleIds(m.getRoleIds());
                continue;
            }
            auth = new Auth();
            auth.setGroupId(groupId);
            auth.setType(m.getType());
            auth.setRoleIds(m.getRoleIds());
            save(auth);
        }
    }

    /**
     * 批量为组织机构、工作职位添加角色授权
     * @author lyd
     * @date 2018年3月9日
     * @param organizationIds
     * @param jobIds
     * @param m
     */
    @Override
    public void addOrganizationJobAuth(Long[] organizationIds, Long[][] jobIds, Auth m) {

        if (ArrayUtils.isEmpty(organizationIds)) {
            return;
        }
        for (int i = 0, l = organizationIds.length; i < l; i++) {
            Long organizationId = organizationIds[i];
            if (jobIds[i].length == 0) {
                addOrganizationJobAuth(organizationId, null, m);
                continue;
            }

            //仅新增/修改一个 spring会自动split（“，”）--->给数组
            if (l == 1) {
                for (int j = 0, l2 = jobIds.length; j < l2; j++) {
                    Long jobId = jobIds[i][0];
                    addOrganizationJobAuth(organizationId, jobId, m);
                }
            } else {
                for (int j = 0, l2 = jobIds[i].length; j < l2; j++) {
                    Long jobId = jobIds[i][0];
                    addOrganizationJobAuth(organizationId, jobId, m);
                }
            }
        }
    }

    /**
     * 根据用户信息获取 角色
     * 1.1、用户  根据用户绝对匹配
     * 1.2、组织机构 根据组织机构绝对匹配 此处需要注意 祖先需要自己获取
     * 1.3、工作职务 根据工作职务绝对匹配 此处需要注意 祖先需要自己获取
     * 1.4、组织机构和工作职务  根据组织机构和工作职务绝对匹配 此处不匹配祖先
     * 1.5、组  根据组绝对匹配
     *
     * @param userId             必须有
     * @param groupIds           可选
     * @param organizationIds    可选
     * @param jobIds             可选
     * @param organizationJobIds 可选
     * @return
     */
    @Override
    public Set<Long> findRoleIds(Long userId, Set<Long> groupIds, Set<Long> organizationIds, Set<Long> jobIds, Set<Long[]> organizationJobIds) {
        return getAuthDao().findRoleIds(userId, groupIds, organizationIds, jobIds, organizationJobIds);
    }
    
    
    private void addOrganizationJobAuth(Long organizationId, Long jobId, Auth m) {
        if (organizationId == null) {
            organizationId = 0L;
        }
        if (jobId == null) {
            jobId = 0L;
        }

        Auth auth = getAuthDao().findByOrganizationIdAndJobId(organizationId, jobId);
        if (auth != null) {
            auth.addRoleIds(m.getRoleIds());
            return;
        }

        auth = new Auth();
        auth.setOrganizationId(organizationId);
        auth.setJobId(jobId);
        auth.setType(m.getType());
        auth.setRoleIds(m.getRoleIds());
        save(auth);
    }
}
