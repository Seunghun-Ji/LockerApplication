package com.example.demo.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
*
* [설명] 	WebUtils class 사용하여 Session에 담겨있는 객체들을 보다 짧은 코드로 넣고 빼게 할 수 있으며
* 		세션 객체나 쿠키를 받아들일 수 있다.
* 		즉, 이를 이용하여 controller를 통해 View(Thymeleaf)에 해당 객체 정보를 줄 수 있다.
* 		본 코드는 UserId: [사용자 ID] ([사용자 권한 리스트]) 로 나타내게 된다.
* 		
* 
* 		참고 사이트: http://whiteship.tistory.com/1227
*
* @file : WebUtils.java
* @package : com.example.demo.utils
* @project : LockerApplication
* @author : Seung-hun Ji
* @since : 2018. 4. 8.
*/
public class WebUtils {
 
    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();
 
        sb.append("UserId:").append(user.getUsername());
 
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }
     
}