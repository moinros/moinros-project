package com.moinros.config.must;

import com.moinros.config.Config;
import com.moinros.project.service.system.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 注释:
 *
 * @Author moinros
 * @Date 2020/2/27 22:56
 * @Verison 1.0
 */
@Configuration
public class InitDataConfig implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(InitDataConfig.class);

    @Autowired
    private SystemService systemService;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("==================== 初始化数据 START \t====================");
        Config.ACCESS = systemService.findAllAccess();
        LOG.info("==================== 初始化数据 END \t====================");
    }

}
