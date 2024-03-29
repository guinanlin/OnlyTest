package com.wansenai.service.system.impl;

import com.wansenai.service.system.ISysMsgService;
import com.wansenai.entities.system.SysMsg;
import com.wansenai.mappers.system.SysMsgMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息表 服务实现类
 * </p>
 *
 * @author James Zow
 * @since 2023-09-05
 */
@Service
public class SysMsgServiceImpl extends ServiceImpl<SysMsgMapper, SysMsg> implements ISysMsgService {

}
