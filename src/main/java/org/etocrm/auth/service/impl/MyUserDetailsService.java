package org.etocrm.auth.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.etocrm.auth.dto.SysUserPermission;
import org.etocrm.auth.dto.ac.entity.SysUser;
import org.etocrm.auth.dto.sass.User;
import org.etocrm.auth.exception.RefuseException;
import org.etocrm.auth.repository.SysUserRepository;
import org.etocrm.auth.repository.UserRepository;
import org.etocrm.auth.security.SecurityUser;
import org.etocrm.database.enums.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查数据库
        log.info("登录的用户名："+username);
        User user = userRepository.findByUid(username);
        SysUser sysUser = sysUserRepository.findByUid(username);
        if(user==null&&sysUser==null) {
            throw new RefuseException(ResponseEnum.FAILD.getCode(),"用户不存在");
        }
//        List<SysUserPermission> ownAuthList = userRepository.loadUserAuthorities(user.getId());

        Collection<GrantedAuthority>  authList = Lists.newArrayList();
//        for(SysUserPermission uacAction: ownAuthList) {
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(uacAction.getMenuRoute());
//            authList.add(simpleGrantedAuthority);
//        }
        return new SecurityUser(user.getId(), user.getUid(), passwordEncoder.encode(user.getPassword()),
                user.getName(), null, user.getName(),user.getStatus(),authList);
    }


}
