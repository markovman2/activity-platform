package com.cong.controller;

import com.cong.dao.UserDao;
import com.cong.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@Service
public class RegisterController {

    @Resource
    private UserDao userDao;

    @Resource
    private JavaMailSenderImpl javaMailSender;

    @GetMapping("/toRegister")
    public String toRegister(HttpSession session) {
        session.invalidate();
        return "register_sendEmail";
    }

    /**
     *
     * @param email: register email if haven't registered
     * @param model:
     * @param session:
     * switch condition:
     *               1 the email has been registered
     *               2 the email hasn't been registered and the register message has been sent
     *               3 the email hasn't been registered and the register message need to be sent
     * @return: the page to go to
     */
    @GetMapping("/registerEmail")
    public String registerEmail(@RequestParam("email")String email,
                                @RequestParam(value="verifyCode", required = false) String verifyCode,
                                Model model, HttpSession session) {
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            model.addAttribute("msg", "该邮箱已被注册！");
            return "register_sendEmail";
        }
        else if (session.getAttribute("registerEmail") != null && session.getAttribute("registerEmail").equals(email)) {
            if (!verifyCode.equals(session.getAttribute("verifyCode"))) {
                model.addAttribute("msg", "验证码错误！请重新输入！");
                return "register_sendEmail";
            }
            session.removeAttribute("verifyCode");
            model.addAttribute("targetEmail", email);
            return "register_setAccountInformation";
        }
        else {
            model.addAttribute("registerEmail", email);
            return sendRegisterEmail(email, model, session);
        }
    }

    @ApiOperation("接收个人信息，完成账号创建")
    @PostMapping("/registerInformation")
    public String registerInformation(@RequestParam @ApiParam(value="email")String email,
                                      @RequestParam @ApiParam(value="password") String password,
                                      @RequestParam @ApiParam(value="passwordConfirm") String passwordConfirm,
                                      @RequestParam @ApiParam(value="userName") String userName,
                                      @RequestParam @ApiParam(value="userNickname") String userNickname,
                                      @RequestParam @ApiParam(value="gender") Integer gender,
                                      HttpSession session
                                      ) {
        if (!password.equals(passwordConfirm)) {
            return "register_setAccountInformation";
        }
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        userDao.save(new User(email, encryptedPassword, userName, userNickname, gender));
        session.invalidate();
        return "redirect:/index.html";
    }

    public String sendRegisterEmail(String email, Model model, HttpSession session) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String code = generateVerifyCode(4);
        mailMessage.setSubject("活动平台注册邮件");
        mailMessage.setText("欢迎您创建大学生活动平台账号，以下是您的注册验证码：" + code);
        mailMessage.setFrom("1360169584@qq.com");
        mailMessage.setTo(email);
        javaMailSender.send(mailMessage);
        model.addAttribute("msg", "注册邮件已发送!");
        session.setAttribute("verifyCode", code);
        session.setAttribute("registerEmail", email);
        return "register_sendEmail";
    }

    public String generateVerifyCode(int verifySize) {
        final String VERIFY_CODES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int codesLen = VERIFY_CODES.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(VERIFY_CODES.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }
}
