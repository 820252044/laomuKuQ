package com.laomukuq.controller;

import com.laomukuq.entity.MailVo;
import com.laomukuq.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author laomu
 * @date 2019-12-07
 * @version 1.0
 */
@RestController
public class MailController     {

    @Autowired
    private MailService mailService;
 
    /**
     * 发送邮件的主界面
     */
    @GetMapping("/")
    public ModelAndView index() {
        //打开发送邮件的页面
        ModelAndView mv = new ModelAndView("mail/sendMail");
        //邮件发信人
        mv.addObject("from", mailService.getMailSendFrom());
        return mv;
    }
    /**
     * 发送邮件
     */
    @PostMapping("/mail/send")
    public MailVo sendMail(@RequestBody MailVo mail, MultipartFile[] files) {
        mail.setMultipartFiles(files);
        //发送邮件和附件
        return mailService.sendMail(mail);
    }
}
