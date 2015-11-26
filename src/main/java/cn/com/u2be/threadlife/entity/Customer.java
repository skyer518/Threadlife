package cn.com.u2be.threadlife.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Ã÷ on 2015/11/26.
 */
@Entity
@Table(name = "customer")
public class Customer {

    private Long id;

    private String name;

    private String contact;

    private String telephone;

    private String email;

    private String remark;


    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    @Column(name = "contact")
    public String getContact() {
        return this.contact;
    }

    @Column(name = "telephone")
    public String getTelephone() {
        return this.telephone;
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    @Column(name = "remark")
    public String getRemark() {
        return this.remark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
