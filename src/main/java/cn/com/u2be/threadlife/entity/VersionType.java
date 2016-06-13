package cn.com.u2be.threadlife.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VersionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VersionType", catalog = "demo")
public class VersionType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String descpt;

	// Constructors

	/** default constructor */
	public VersionType() {
	}

	/** full constructor */
	public VersionType(String name, String descpt) {
		this.name = name;
		this.descpt = descpt;
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

	@Column(name = "name", length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "descpt")
	public String getDescpt() {
		return this.descpt;
	}

	public void setDescpt(String descpt) {
		this.descpt = descpt;
	}

}