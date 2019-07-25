package gdyj.tydic.com.jinlingapp.bean;

import java.util.List;

/**
 *   接口返回数据格式
 * @author scott
 * @email jeecgos@163.com
 * @date  2019年1月19日
 */

public class BaseResult<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */

	private boolean success = true;

	/**
	 * 返回处理消息
	 */

	private String message = "操作成功！";

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}


	/**
	 * 返回代码
	 */

	private Integer code = 0;
	
	/**
	 * 返回数据对象 data
	 */

	//private T result;

	private List<T> result;


	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
}