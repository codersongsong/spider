package com.spider.song.dubbo.bean;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <Description> service dubbo服务对象传输Object Value对象 </Description>
 * <ClassName> SpiderParametersVo </ClassName>
 *
 * @Author generator
 * @Date 2018年01月23日 18时:28分:31秒
 */
public class SpiderParametersVo implements Serializable{

	private static final long serialVersionUID = -8886267161275214875L;

	/**业务来源：010，PC端；020，移动端*/
	private String sources;
	
	/**业务版本号，默认:0.1*/
	private String version = "0.1";
	
	/**业务类型，与服务层服务类方法同名*/
	private String businessType;
	
	/**日志关联key*/
	private String logAndKey;
	
	/**业务参数集合*/
	private Map<String , Object> map = null;
	
	/**错误码：0000,成功；0001,重复请求；0002,失败；其它错误码自定*/
	private String resCode;
	
	/**错误信息，默认为“成功”*/
	private String resDesc;
	
	public SpiderParametersVo(){
		map = new HashMap<String , Object>();
	}
	
	public SpiderParametersVo(String sources){
		this.sources = sources;
		map = new HashMap<String , Object>();
	}
	
	public SpiderParametersVo(String sources , String businessType){
		this.sources = sources;
		this.businessType = businessType;
		map = new HashMap<String , Object>();
	}

	/**业务来源：010，PC端；020，移动端*/
	public String getSources() {
		return sources;
	}

	/**业务来源：01，PC端；02，移动端*/
	public void setSources(String sources) {
		this.sources = sources;
	}

	/**日志关联key*/
	public String getLogAndKey() {
		return logAndKey;
	}

	/**日志关联key*/
	public void setLogAndKey(String logAndKey) {
		this.logAndKey = logAndKey;
	}

	/**业务版本号，默认:0.1*/
	public String getVersion() {
		return version;
	}

	/**业务版本号，默认:0.1*/
	public void setVersion(String version) {
		this.version = version;
	}

	/**业务类型，与服务层服务类方法同名*/
	public String getBusinessType() {
		return businessType;
	}

	/**业务类型，与服务层服务类方法同名*/
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	/**业务参数*/
	public void setInfo(String key , Object value){
		map.put(key, value);
	}
	
	/**业务参数*/
	public Object getInfo(String key){
		return map.get(key);
	}
	
	/**业务参数*/
	public String getString(String key){
		Object obj = map.get(key);
		String returnValue = "";
		if(obj != null){
			if(obj.getClass().getSimpleName().equals("BigDecimal")){
				returnValue =((BigDecimal) obj).toPlainString();
			}else{
				returnValue = String.valueOf(obj);
			}
		}
		return returnValue;
	}
	
	/**业务参数*/
	public BigDecimal getBigDecimal(String key){
		Object obj = map.get(key);
		if(obj != null){
			if(obj.getClass().getSimpleName().equals("BigDecimal")){
				return ((BigDecimal) obj);
			}else{
				return new BigDecimal(String.valueOf(obj));
			}
		}
		return null;
	}
	
	/**业务参数*/
	public Integer getInt(String key){
		Object obj = map.get(key);
		if(obj != null){
			if(obj.getClass().getSimpleName().equals("Integer")){
				return (Integer) obj;
			}else{
				return Integer.valueOf(String.valueOf(obj));
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder resultStr = new StringBuilder();
		resultStr.append(" businessType: " + this.getBusinessType());
		resultStr.append(" sources: " + this.getSources());
		resultStr.append(" version: " + this.getVersion());
		resultStr.append(" logAndKey: " + this.getLogAndKey());
		if(!StringUtils.isEmpty(this.getResCode())){
			resultStr.append(" resCode: " + this.getResCode());
			resultStr.append(" resDesc: " + this.getResDesc());
		}
		resultStr.append(" map: " + this.map.toString());
		return resultStr.toString();
		
	}
	
	public void remove(String key){
		map.remove(key);
	}

	/**错误码：0000,成功；0001,重复请求；0002,失败；其它错误码自定*/
	public String getResCode() {
		return resCode;
	}

	/**错误码：0000,成功；0001,重复请求；0002,失败；其它错误码自定*/
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	/**错误信息，默认为“成功”*/
	public String getResDesc() {
		return resDesc;
	}

	/**错误信息，默认为“成功”*/
	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
