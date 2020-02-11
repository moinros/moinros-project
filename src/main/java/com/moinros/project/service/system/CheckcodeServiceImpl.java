package com.moinros.project.service.system;

import com.moinros.project.model.dao.system.CheckcodeMapper;
import com.moinros.project.model.dto.enums.CheckcodeState;
import com.moinros.project.model.pojo.system.Checkcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckcodeServiceImpl implements CheckcodeService {

    @Autowired
    private CheckcodeMapper mapper;

    @Override
    public boolean addCheckcode(Checkcode checkcode) {
        checkcode.setCheckcodeTimeLimit(System.currentTimeMillis());
        Checkcode code = mapper.selectCheckcode(checkcode.getCheckcodeKey());
        Integer num = 0;
        if (code == null) {
            num = mapper.insertCheckcode(checkcode);
        } else {
            checkcode.setCheckcodeStatus(true);
            num = mapper.updateCheckcode(checkcode);
        }
        return num != null && num.intValue() > 0 ? true : false;
    }

    @Override
    public boolean removeCheckcodeByKey(String key) {
        Checkcode code = mapper.selectCheckcode(key);
        if (code != null) {
            code.setCheckcodeValue("-");
            code.setCheckcodeStatus(false);
            code.setCheckcodeTimeLimit((long) 1);
            Integer num = mapper.updateCheckcode(code);
            return num != null && num.intValue() > 0 ? true : false;
        }
        return false;
    }

    @Override
    @Transactional
    public CheckcodeState verifyThatCheckcode(Checkcode checkcode) {
        if (checkcode != null) {
            Checkcode code = mapper.selectCheckcode(checkcode.getCheckcodeKey());
            if (code != null) {
                if (code.getCheckcodeKey() != null && code.getCheckcodeValue() != null) {
                    if (code.getCheckcodeKey().equals(checkcode.getCheckcodeKey()) && code.getCheckcodeValue().equals(checkcode.getCheckcodeValue())) {
                        if (code.getCheckcodeStatus() != null && code.getCheckcodeStatus()) {
                            if (code.getCheckcodeTimeLimit() + 1800000 < System.currentTimeMillis()) {
                                return CheckcodeState.overtime; // 超时
                            } else {
                                removeCheckcodeByKey(code.getCheckcodeKey());
                                return CheckcodeState.success; // 验证通过
                            }
                        } else {
                            return CheckcodeState.invalid; // 失效
                        }
                    }
                }
                return CheckcodeState.mismatch; // 验证码不匹配
            } else {
                return CheckcodeState.error; // 没有查询到
            }
        } else {
            return CheckcodeState.isnull; // 传入了空值
        }
    }
}
