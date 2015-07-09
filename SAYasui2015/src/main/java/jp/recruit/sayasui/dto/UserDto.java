package jp.recruit.sayasui.dto;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION)
public class UserDto implements Serializable {
	private static final long serialVersionUID = -6525136963154540867L;
	public String name;
	public String descript;
	public String role;
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return descript
	 */
	public String getDescript() {
		return descript;
	}
	/**
	 * @param descript セットする descript
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}
	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role セットする role
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
