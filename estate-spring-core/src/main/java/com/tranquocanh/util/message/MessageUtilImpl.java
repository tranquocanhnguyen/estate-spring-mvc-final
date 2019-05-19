package com.tranquocanh.util.message;

import javax.servlet.http.HttpServletRequest;

public class MessageUtilImpl implements MessageUtil {

    private String messsage;
    private String alert;

    public MessageUtilImpl(String message, String alert) {
        this.messsage = message;
        this.alert = alert;
    }

    @Override
    public void buildMessage(HttpServletRequest request) {
        request.setAttribute("message",this.messsage);
        request.setAttribute("alert",this.alert);
    }
}
