package com.laomukuq.service;

import com.laomukuq.entity.MailVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author laomu
 * @date 2019-12-07
 * @version 1.0
 */
@Service
public class MailService {

    /**
     * 提供日志类
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 注入邮件工具类
     */
    @Autowired
    private JavaMailSenderImpl mailSender;


    /**
     * 发送邮件
     */
    public MailVo sendMail(MailVo mail) {
        try {
            //1.检测邮件
            checkMail(mail);
            //2.发送邮件
            sendMimeMail(mail);
            //3.保存邮件
            return saveMail(mail);
        } catch (Exception e) {
            logger.error("发送邮件失败:", e);
            mail.setStatus("fail");
            mail.setError(e.getMessage());
            return mail;
        }
    }

    /**
     * 检测邮件信息类
     * @param mail
     */
    private void checkMail(MailVo mail) {
        if (StringUtils.isEmpty(mail.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mail.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mail.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    /**
     * 构建复杂邮件信息类
     * @param mail
     */
    private void sendMimeMail(MailVo mail) {
        try {
            //true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            //邮件发信人从配置项读取
            mail.setFrom(getMailSendFrom());
            //邮件发信人
            messageHelper.setFrom(mail.getFrom());
            //邮件收信人
            messageHelper.setTo(mail.getTo().split(","));
            messageHelper.setSubject(mail.getSubject());
            //邮件主题
            messageHelper.setText(mail.getText());
            //邮件内容
            if (!StringUtils.isEmpty(mail.getCc())) {
                //抄送
                messageHelper.setCc(mail.getCc().split(","));
            }
            if (!StringUtils.isEmpty(mail.getBcc())) {
                //密送
                messageHelper.setCc(mail.getBcc().split(","));
            }
            if (mail.getMultipartFiles() != null) {
                //添加邮件附件
                for (MultipartFile multipartFile : mail.getMultipartFiles()) {
                    // 判断文件是否为空 名字是否为空
                    if(multipartFile != null && !multipartFile.isEmpty() && !StringUtils.isBlank(multipartFile.getOriginalFilename())){
                        messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                    }
                }
            }
            //发送时间
            if (mail.getSentDate() == null || StringUtils.isEmpty(mail.getSentDate().toString())) {
                mail.setSentDate(new Date());
                messageHelper.setSentDate(mail.getSentDate());
            }
            //正式发送邮件
            mailSender.send(messageHelper.getMimeMessage());
            mail.setStatus("ok");
            logger.info("发送邮件成功：{}->{}", mail.getFrom(), mail.getTo());
        } catch (Exception e) {
            //发送失败
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存邮件
     * @param mail
     * @return
     */
    private MailVo saveMail(MailVo mail) {
        //将邮件保存到数据库..
        return mail;
    }

    /**
     * 获取邮件发信人
     * @return
     */
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }
}
