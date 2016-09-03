package com.ejunhai.qutihuo.common.base;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * TODO: 增加描述
 * 
 * @author she.yj
 * @date 2015-3-2 下午4:55:36
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class JsonDateSerializer19 extends JsonSerializer<Timestamp>{

	@Override
	public void serialize(Timestamp arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(arg0);
		arg1.writeString(formattedDate);
		
	}

}
