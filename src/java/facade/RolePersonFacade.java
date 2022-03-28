/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import entity.RolePerson;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class RolePersonFacade extends AbstractFacade<RolePerson> {

    @PersistenceContext(unitName = "WebShoesLibraryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolePersonFacade() {
        super(RolePerson.class);
    }
    public boolean isRole(String roleName, Person authPerson) {
        List<String> listRoleNames = em.createQuery("SELECT rp.role.roleName FROM RolePerson rp WHERE rp.person = :authPerson")
                .setParameter("authPerson", authPerson)
                .getResultList();
        if(listRoleNames.contains(roleName)){
            return true;
        }else{
            return false;
        }
    }

    public String getTopRole(Person person) {
        List<String> listRoleNames = em.createQuery("SELECT rp.role.roleName FROM RolePerson rp WHERE rp.person = :person")
                .setParameter("person", person)
                .getResultList();
        if(listRoleNames.contains("ADMINISTRATOR"))return "ADMINISTRATOR";
        if(listRoleNames.contains("MANAGER"))return "MANAGER";
        if(listRoleNames.contains("READER"))return "READER";
        return null;
    }
    
}
