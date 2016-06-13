package cn.com.u2be.threadlife.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Version entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Version", catalog = "demo", uniqueConstraints = @UniqueConstraint(columnNames = "type"))
public class Version implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer type;
	private Integer versionCode;
	private String versionName;
	private Timestamp modifyTime;
	private String descpt;
	private String temp;

	// Constructors

	/** default constructor */
	public Version() {
	}

	/** full constructor */
	public Version(Integer type, Integer versionCode, String versionName,
			Timestamp modifyTime, String descpt, String temp) {
		this.type = type;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.modifyTime = modifyTime;
		this.descpt = descpt;
		this.temp = temp;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "type", unique = true)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "version_code")
	public Integer getVersionCode() {
		return this.versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	@Column(name = "version_name", length = 20)
	public String getVersionName() {
		return this.versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	@Column(name = "modify_time", length = 0)
	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "descpt")
	public String getDescpt() {
		return this.descpt;
	}

	public void setDescpt(String descpt) {
		this.descpt = descpt;
	}

	@Column(name = "temp")
	public String getTemp() {
		return this.temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

}