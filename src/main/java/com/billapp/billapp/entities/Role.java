package com.billapp.billapp.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "Role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleid;

    private String authority;

    public Role(){
        super();
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public Role(Integer id, String authority) {
        this.roleid = id;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setRoleid(Integer id) {
        this.roleid = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
