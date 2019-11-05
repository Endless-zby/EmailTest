package club.zby.email.controller;

import club.zby.email.EmailTemplate.HttpEmailFace;
import club.zby.email.Until.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

/**
 * @Author: 赵博雅
 * @Date: 2019/11/5 10:03
 */

@Api(value = "邮件发送测试单元")
@Controller
@RequestMapping(value = "test")
public class SendMail {

    @Autowired
    private HttpEmailFace httpEmailFace;



    @ResponseBody
    @ApiOperation(value="邮件", notes="SMTP邮件测试，输入对方邮箱 例如：381016296@qq.com ")
    @GetMapping("sendmail")
    public Result sendmail(@RequestParam("email") String email){

        //用时间戳来模拟随机id
        return httpEmailFace.httpEmail("123456", email);

    }

    @ResponseBody
    @ApiOperation(value="邮件", notes="邮件验证测试")
    @GetMapping("/checkmail/{id}")
    public ModelAndView checkemail(@PathVariable("id") String id) throws ParseException {

        if("123456".equals(id)){

            return new ModelAndView("checkEmail", "result", "验证成功！");
        }

        return new ModelAndView("checkEmail", "result", "验证超时！");
    }


}
