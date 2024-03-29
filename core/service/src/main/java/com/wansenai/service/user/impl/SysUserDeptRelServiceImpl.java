package com.wansenai.service.user.impl;

import com.wansenai.service.user.ISysUserDeptRelService;
import com.wansenai.entities.user.SysUserDeptRel;
import com.wansenai.mappers.user.SysUserDeptRelMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门用户关系表 服务实现类
 * </p>
 *
 * @author James Zow
 * @since 2023-09-05
 */
@Service
public class SysUserDeptRelServiceImpl extends ServiceImpl<SysUserDeptRelMapper, SysUserDeptRel> implements ISysUserDeptRelService {

    @Override
    public List<SysUserDeptRel> queryBatchByUserIds(List<Long> userIds) {
        return lambdaQuery()
                .in(SysUserDeptRel::getUserId, userIds)
                .list();
    }

    @Override
    public List<SysUserDeptRel> queryByUserId(Long userIds) {
        return lambdaQuery()
                .eq(SysUserDeptRel::getUserId, userIds)
                .list();
    }
}
