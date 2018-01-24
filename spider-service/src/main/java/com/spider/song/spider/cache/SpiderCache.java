package com.spider.song.spider.cache;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <Description> 应用级缓存 </Description>
 * <ClassName> SpiderCache </ClassName>
 *
 * @Author generator
 * @Date 2018年01月23日 18时:28分:31秒
 */
public class SpiderCache {

	public static ConcurrentMap<String, String> business = new ConcurrentHashMap<String,String>();
	
}
