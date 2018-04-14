package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
 
import com.example.demo.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
*
* [설명] 	Database의 Entity에 접근하여 데이터를 조회하는 기능으로
* 		사용자의 정보를 AppUser 객체를 통해 가져온다.
*
* @file : AppUserDAO.java
* @package : com.example.demo.dao
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
* @link : https://o7planning.org/en/11705/create-a-login-application-with-spring-boot-spring-security-jpa#a13944416
*/
@Repository
@Transactional
public class AppUserDAO {
 
    @Autowired
    private EntityManager entityManager;
 
    public AppUser findUserAccount(String userId) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userId = :userId ";
 
            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userId", userId); // 위의 :userId 자리에 findUserAccount 파라미터로 받은 데이터를 넣는다.
 
            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}