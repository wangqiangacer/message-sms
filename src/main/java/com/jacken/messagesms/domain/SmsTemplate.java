package com.jacken.messagesms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsTemplate extends BaseEntity {

	private static final long serialVersionUID = 5316519743806558379L;
	
	private String sceneCode;
	private String template;
	private String channelCode;
	private String memo;

}
