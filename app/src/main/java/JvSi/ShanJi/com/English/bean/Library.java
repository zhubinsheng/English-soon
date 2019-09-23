package JvSi.ShanJi.com.English.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import io.objectbox.annotation.Id;

/**
 * @Description: 类别表
 * @Author: jeecg-boot
 * @Date:   2019-08-27
 * @Version: V1.0
 */
public class Library implements MultiItemEntity {

	private String classify;

	private String library;

	private boolean isStudy;

	@Id
	public long boxId;

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * id
	 */
	private String id;

	@Override
	public int getItemType() {
		return 2;
	}

	public boolean isStudy() {
		return isStudy;
	}

	public void setStudy(boolean study) {
		isStudy = study;
	}
}
