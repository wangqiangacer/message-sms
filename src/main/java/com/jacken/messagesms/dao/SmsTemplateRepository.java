package com.jacken.messagesms.dao;

import com.jacken.messagesms.domain.SmsTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SmsTemplateRepository {

	
	
	@Select("select * from sms_template where scene_code=#{sceneCode} limit 1")
	SmsTemplate selectBySceneCode(String sceneCode);
	 
}
