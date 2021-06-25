package org.etocrm.auth.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

    public static String getCurrentLoginName() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof DefaultOAuth2User) {

            return ((DefaultOAuth2User) principal).getName();

        }

        if (principal instanceof UserDetails) {

            return ((UserDetails) principal).getUsername();

        }

        return String.valueOf(principal);

    }

    public static Set<String> getCurrentAuthorityUrl() {
        Collection<? extends GrantedAuthority> authorities =null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authorities = authentication.getAuthorities();


        Set<String> path = new HashSet<>();
    //    Set urls = redisTemplate.opsForSet().members("urls");
    //    redisTemplate.keys("urls").clear();
     //   urls.forEach(url->path.add(url.toString()));

        //  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority authority : authorities) {
            String url = authority.getAuthority().replace("ROLE_","");
            if (StringUtils.isNotEmpty(url)) {
                path.add(url);
            }
        }

        return path;
    }


}
