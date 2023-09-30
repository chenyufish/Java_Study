package com.fishman.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishman.usercenter.Mapper.UserMapper;
import com.fishman.usercenter.model.domain.User;
import com.fishman.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author fishman
* &#064;description  针对表【user(用户表)】的数据库操作Service实现
* &#064;createDate  2023-09-29 15:05:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Resource
    private UserMapper userMapper;
    /**
     * 盐值
     */
    private static final String SALT ="fishman";

    /**
     * 用户登录键值
     */
    private static final String USER_LOGIN_STATE ="userLoginState";
    @Override
    public long userRegister(String userAccount, String userPasswod, String checkPassword) {
        // todo 修改为自定义异常
        //校验
        if (StringUtils.isAnyBlank(userAccount,userPasswod,checkPassword)){
            return -1;
        }
        if (userAccount.length()<4){
            return -1;
        }
        if (userPasswod.length()<8||checkPassword.length()<8){
            return -1;
        }
         //账户不能包含特殊字符

        String str = "[`~@!#$%^&*()+=|{}'Aa:;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(str).matcher(userAccount);
        if (matcher.find()){
            return -1;
        }

        //账户不能重复
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count>0){
            return -1;
        }
        //密码和检验密码是否相同
        if(!userPasswod.equals(checkPassword)) {
            return -1;
        }

        //对密码进行加密加盐
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT+"mypassword").getBytes());
        //插入数据
        User user =new User();
        user.setUserAccount(userAccount);
        user.setUserPasswrod(encryptPassword);
        boolean saveResult= this.save(user);
        if (!saveResult){
            return -1;
        }
        return user.getId();
    }

    @Override
    public User doLogin(String userAccount, String userPasswod, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(userAccount,userPasswod)){
            return null;
        }
        if (userAccount.length()<4){
            return null;
        }
        if (userPasswod.length()<8){
            return null;
        }
        //账户不能包含特殊字符

        String str = "[`~@!#$%^&*()+=|{}'Aa:;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(str).matcher(userAccount);
        if (matcher.find()){
            return null;
        }
        //对密码进行加密加盐
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT+"mypassword").getBytes());

        //账户不能重复
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        //用户不存在
        if (user==null){
            log.error("user login failed!userAccout cannot match userPassword");
            return null;
        }

        //用户数据
        User safetyUser=new User();
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setGenter(user.getGenter());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreatetime(LocalDateTime.now());
        request.getSession().getAttribute(USER_LOGIN_STATE);
        //记录用户登陆状态，返回用户脱敏信息
return safetyUser;
    }
}




