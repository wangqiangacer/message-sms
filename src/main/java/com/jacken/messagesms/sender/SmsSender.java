package com.jacken.messagesms.sender;


import com.jacken.messagesms.exception.MessageException;

public interface SmsSender {

	void send(String mobileNo, String smsMessage) throws MessageException;
}
