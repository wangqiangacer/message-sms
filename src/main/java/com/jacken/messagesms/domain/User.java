package com.jacken.messagesms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User extends BaseEntity {

	private static final long serialVersionUID = -6997713367550513449L;

	private String realName;
	private String nickName;
	private String mobileNo;
	private Integer isVip;
	private String avatarUrl;
	private Date lastLoginTime;
	private Date registerTime;
	private String sourceCode;
	private String wechatOpenid;
	private Date vipTime;
	private String channelCode;

}
