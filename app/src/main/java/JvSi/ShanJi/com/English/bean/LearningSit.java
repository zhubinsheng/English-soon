package JvSi.ShanJi.com.English.bean;

/**
 * @Description: 学习情况记录表
 * @Author: jeecg-boot
 * @Date:   2019-08-29
 * @Version: V1.0
 */

public class LearningSit {
    
	/**user id*/

	private String userid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**classifyId*/

	private String classifyId;

	/**count*/

	private String count;

	private String uuid;

}
