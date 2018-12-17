package com.jhkj.sso_demo.controller;

import com.jhkj.face2019_demo.common.JsonData;
import com.jhkj.face2019_demo.dto.UserDto;
import com.jhkj.face2019_demo.entity.SysUser;
import com.jhkj.face2019_demo.service.SysUserService;
import com.jhkj.face2019_demo.util.WebUtil;
import com.jhkj.face2019_demo.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther: LinYan
 * @date: 2018/11/20 9:32
 * @description: 用户相关控制类
 */
@Slf4j
@Controller
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping(value = {"/", "/index"})
    public ModelAndView index() {
        //获取当前的用户信息
        Subject currentUser = SecurityUtils.getSubject();
        //判断用户是通过"记住我"功能自动登录,此时session失效
        if (!currentUser.isAuthenticated() && currentUser.isRemembered()) {
            try {
                //从凭证集合中获取用户信息
                SysUser user = (SysUser) currentUser.getPrincipals().getPrimaryPrincipal();

                //把当前用户信息放入session
                Session session = currentUser.getSession();
                session.setAttribute("user", user);
            } catch (Exception e) {
//                e.printStackTrace();
                //自动登录失败,跳转到登录页面
                return new ModelAndView("login", JsonData.fail("自动登录失败").toMap());
            }
        }
        return new ModelAndView("index");
    }

    /**
     * 添加用户
     *
     * @param request 页面发送来的请求信息
     * @return 注册成功跳转到登录页面，注册失败返回注册页面
     */
    @RequestMapping("/userAdd")
    public ModelAndView userAdd(HttpServletRequest request) {
        //获取页面传送的数据
        UserVO userVO = WebUtil.request2Bean(request, UserVO.class);
        //将VO对象进行转换
        UserDto userDto = UserDto.adapt2Dto(userVO);
        //用户信息验证
        JsonData jsonData = userDto.checkInfo();
        if (jsonData.isRet()) {
            //如果添加成功跳转到后台页面
            return new ModelAndView("index", jsonData.toMap());
        } else {
            //如果添加失败给出提示
            return new ModelAndView("userAdd", jsonData.toMap());
        }
    }

    /**
     * 查询用户
     *
     * @param account 账户名
     * @return 查询到的用户信息
     */
//    @ResponseBody
//    @RequestMapping("/queryUser")
//    @RequiresPermissions("user:query")
//    public User queryUser(@RequestParam("account") String account) {
//        return userService.findByAccount(account);
//    }

    /**
     * 用户登录，根据抛出的异常信息会给出不同的提示信息
     *
     * @param request 页面发送来的请求信息
     * @return 页面的逻辑地址
     */
    @RequestMapping("/userLogin")
    public ModelAndView login(HttpServletRequest request) {
        //将页面发送来的信息封装到VO对象中
        UserVO userVO = WebUtil.request2Bean(request, UserVO.class);
        //获取当前主体
        Subject subject = SecurityUtils.getSubject();

        //创建token
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getAccount(), userVO.getPassword());

        //如果用户选择了rememberMe
        if (userVO.getRememberMe() != null) {
            token.setRememberMe(true);
        }
        try {
            //进行登录认证
            log.info("正在对账户：" + userVO.getAccount() + "进行登录认证...");
            //进行登录验证
            subject.login(token);
            //如果验证成功
            if (subject.isAuthenticated()) {
                log.info("认证成功...");
                //获取User，将User数据设置进session后返回页面
                SysUser user = sysUserService.findUser(userVO.getAccount());

                Session session = subject.getSession();
                session.setAttribute("user", user);
                //返回JsonData封装的UserVo对象
                return new ModelAndView("index");
            } else {
                return new ModelAndView("login", JsonData.fail("登录失败!").toMap());
            }
            // 登录失败将抛出的异常信息获取后进行封装显示
        } catch (UnknownAccountException unknownAccountException) {
            log.info("无法识别的账户名 " + unknownAccountException.getMessage());
            return new ModelAndView("login", JsonData.fail("无法识别的账户名!").toMap());
        } catch (IncorrectCredentialsException incorrectCredentialsException) {
            log.info("密码不正确 " + incorrectCredentialsException.getMessage());
            return new ModelAndView("login", JsonData.fail("密码不正确!").toMap());
        } catch (AuthenticationException authenticationException) {
            log.info("账户不存在 " + authenticationException.getMessage());
            return new ModelAndView("login", JsonData.fail("账户不存在!").toMap());
        } catch (AuthorizationException exception) {
            log.info("权限异常 " + exception.getMessage());
            return new ModelAndView("login", JsonData.fail("权限异常!" + exception.getMessage()).toMap());
        }
    }

//    @RequestMapping("/registerUser")
//    @ResponseBody
//    @RequiresPermissions("user:add")
//    public boolean register(HttpServletRequest request) {
//        User user = WebUtiles.request2Bean(request, User.class);
//        String[] roleIds = request.getParameterValues("roleIds");
//        String roleString = "";
//        for (String roleId : roleIds) {
//            roleString = roleString + roleId;
//            roleString += ",";
//        }
//        roleString = roleString.substring(0, roleString.length() - 1);
//        user.setRoleIds(roleString);
//        return userService.register(user);
//    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }
}
