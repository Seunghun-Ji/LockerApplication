package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
 
import com.example.demo.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
*
* [설명] 	Database의 Entity에 접근하여 데이터를 조회하는 기능으로
* 		사용자의 권한 정보를 UserRole 객체를 통해 가져온다.
*
* @file : AppRoleDAO.java
* @package : com.example.demo.dao
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
* @link : https://o7planning.org/en/11705/create-a-login-application-with-spring-boot-spring-security-jpa#a13944416
*/
@Repository
@Transactional
public class AppRoleDAO {
 
    @Autowired
    private EntityManager entityManager;
 
    public List<String> getRoleNames(Long userNo)
    {
        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
                + " where ur.appUser.userNo = :userNo ";
 
        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userNo", userNo); //위의 :userNo 자리에 getRoleNames 파라미터로 받은 데이터를 넣는다.
        return query.getResultList();
    }
 
}