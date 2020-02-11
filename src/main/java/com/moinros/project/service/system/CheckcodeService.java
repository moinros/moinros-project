package com.moinros.project.service.system;

import com.moinros.project.model.dto.enums.CheckcodeState;
import com.moinros.project.model.pojo.system.Checkcode;

/**
 * 注释: 定义了与验证码相关的业务接口
 *
 * @Author moinros
 * @Date 2020/2/11 18:19
 * @Verison 1.0
 */
public interface CheckcodeService {

    /**
     * 注释: 添加新的验证码数据
     *
     * @param checkcode 验证码数据实体
     * @return boolean 成功返回true,失败返回false
     */
    boolean addCheckcode(Checkcode checkcode);

    /**
     * 注释: 根据验证码的Key删除指定的验证码数据
     *
     * @param key 验证码key[通常为邮箱,或者手机号]
     * @return boolean 成功返回true,失败返回false
     */
    boolean removeCheckcodeByKey(String key);

    /**
     * 注释: 验证验证码数据是否匹配
     *
     * @param checkcode 需要验证的验证码实体
     * @return ResultCode 返回验证结果的状态码
     */
    CheckcodeState verifyThatCheckcode(Checkcode checkcode);

}
