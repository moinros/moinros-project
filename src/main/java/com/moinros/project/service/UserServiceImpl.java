package com.moinros.project.service;

import com.moinros.project.common.annotation.tool.ParamIsNull;
import com.moinros.project.common.annotation.base64.Base64Decoder;
import com.moinros.project.common.annotation.base64.Base64Encoder;
import com.moinros.project.common.annotation.base64.Base64EncoderParam;
import com.moinros.project.model.dao.UserDataMapper;
import com.moinros.project.model.dto.ResultValue;
import com.moinros.project.model.dto.enums.UserServiceState;
import com.moinros.project.model.dto.vo.ClientSide;
import com.moinros.project.model.dto.vo.ResultStateValue;
import com.moinros.project.model.pojo.UserData;
import com.moinros.project.tool.cipher.Token;
import com.moinros.project.tool.util.date.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/7 14:55
 * @Verison 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDataMapper mapper;

    @Override
    @Transactional
    @Base64Decoder
    public ResultValue<UserServiceState, UserData> login(String username, String password, ClientSide client) {
        if (username != null && password != null && client != null) {
            UserData data = mapper.selectPasswordByName(username);
            ResultValue rv = new ResultStateValue();
            if (data != null) {
                Token t = new Token();
                boolean f = t.validate(password, data.getPassword());
                if (f) {
                    UserData user = mapper.selectUserDataById(data.getUserId());
                    if (user != null) {
                        rv.setState(UserServiceState.LOGIN_SUCCESS);
                        rv.setValue(user);
                        return rv;
                    }
                }
            }
            rv.setState(UserServiceState.LOGIN_PASSWORD);
            return rv;
        }
        return null;
    }

    @Override
    @Transactional
    @ParamIsNull(paramName = {"nickName", "userEmail", "password"})
    @Base64Decoder
    @Base64Encoder(param = {@Base64EncoderParam(name = "nickName", type = String.class)})
    public ResultValue<UserServiceState, UserData> register(String nickName, String userEmail, String password) {
        ResultValue<UserServiceState, UserData> rv = new ResultStateValue<>();
        if (nickName != null && userEmail != null && password != null) {
            UserData user = mapper.getUserByEmail(userEmail);
            if (user != null) {
                rv.setState(UserServiceState.IS_EXIST);
                rv.setValue(user);
            } else {
                Token token = new Token();
                String cipher = token.addSalt(password);
                Integer id = mapper.getUserIdValueIsMax();
                user = new UserData();
                user.setUserId(id == null ? 1001000 : id.intValue() + 1);
                user.setUserName(user.getUserId().toString());
                user.setNickName(nickName);
                user.setUserEmail(userEmail);
                user.setPassword(cipher);
                user.setRegisterTime(DateFormatUtil.getDateTime());
                Integer num = mapper.insertUserData(user);
                if (num != null && num.intValue() > 0) {
                    // 注册成功！
                    rv.setState(UserServiceState.REGISTER_SUCCESS);
                    rv.setValue(user);
                } else {
                    rv.setState(UserServiceState.REGISTER_ERROR);
                }
            }
        } else {
            rv.setState(UserServiceState.DATA_INCOMPLETE);
        }
        return rv;
    }

    @Override
    public boolean mailboxExist(String userEmail) {
        Integer id = mapper.selectEmailEx(userEmail);
        if (id != null && id.intValue() > 1) {
            return true;
        }
        return false;
    }

    @Override
    @Base64Encoder(param = {@Base64EncoderParam(name = "nickname", type = String.class)})
    public boolean nicknameExist(String nickname) {
        Integer id = mapper.selectNickNameEx(nickname);
        if (id != null && id.intValue() > 1) {
            return true;
        }
        return false;
    }

    @Override
    public UserData findUserById(Integer userId) {
        return mapper.selectUserDataById(userId);
    }

    @Override
    public ResultValue<UserServiceState, UserData> modifyUserData(UserData userdata) {
        return null;
    }

    @Override
    public ResultValue<UserServiceState, String> resetPassword(String userEmail, String password) {
        return null;
    }
}
