package cn.com.u2be.threadlife.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employee", catalog = "demo")
public class Employee implements java.io.Serializable {

	// Fields

	private Long id;
	private String contact;
	private String email;
	private String name;
	private String remark;
	private String telephone;

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Employee(Long id, String contact, String email, String name,
			String remark, String telephone) {
		this.id = id;
		this.contact = contact;
		this.email = email;
		this.name = name;
		this.remark = remark;
		this.telephone = telephone;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "contact")
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "telephone")
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}