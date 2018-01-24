package com.spider.song.spider.dubbo.bean;

/**
 * <Description> service常量类 </Description>
 * <ClassName> SpiderConstants </ClassName>
 *
 * @Author generator
 * @Date 2018年01月23日 18时:28分:31秒
 */
public class SpiderConstants {
	
	/**返回结果码：0000成功；*/
	public final static String RESULT_SUCCESS = "0000";
	
	/**返回结果码：0001，重复入库；*/
	public final static String RESULT_REPEAT = "0001";
	
	/**返回结果码：0003，过期。*/
	public final static String RESULT_EXPIRE = "0003"; 
	
	/**返回结果码：0002，失败。*/ 
	public final static String RESULT_FAIL = "0002"; 

	/**返回结果码：5555,接口调用权限限制*/
	public final static String CHANNEL_LIMIT = "5555"; 
	
}
