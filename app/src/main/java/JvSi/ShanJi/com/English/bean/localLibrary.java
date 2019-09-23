package JvSi.ShanJi.com.English.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * @Description: 本地类别表
 * @Author: jeecg-boot
 * @Date:   2019-08-27
 * @Version: V1.0
 */

@Entity
public class localLibrary  {


	public localLibrary(String stringList) {
		this.stringList = stringList;
	}

	@Id
	public long boxId;

	private String stringList;


	public String getStringList() {
		return stringList;
	}

	public void setStringList(String stringList) {
		this.stringList = stringList;
	}
}
