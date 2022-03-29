/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author user
 */
@Entity
public class RolePerson implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade=CascadeType.DETACH)
    private User user;
    @OneToOne(cascade=CascadeType.DETACH)
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.user);
        hash = 47 * hash + Objects.hashCode(this.role);
        return hash;
    }
    @Override
    public boolean equals(Object obj){
        if (this==obj){
            return true;
        }
        if (obj==null){
            return false;
        }
        if (getClass()!=obj.getClass()){
            return false;
        }
        final RolePerson other=(RolePerson) obj;
        if (!Objects.equals(this.id, other.id)){
            return false;
        }
        if (!Objects.equals(this.user, other.user)){
            return false;
        }
        if (!Objects.equals(this.role, other.role)){
            return false;
        }
        return true; 
    }
    
    
    @Override
    public String toString() {
        return "RolePerson{" + "id=" + id + ", user=" + user + ", role=" + role + '}';
    }
    
    
}
