package com.boot.service.User;

import com.boot.persistence.domain.User;
import com.boot.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2017/4/25 14:18
 */
@Service
public class UserService { //implements UserDetailsService {

//    /**
//     *
//     */
//    @Autowired
//    private UserRepository userRepository;
//
//    /**
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
//        User user = this.userRepository.findByName(username);
//        return Objects.isNull(user) ? null : new UserInfo(user);
//    }
//
//
//    class UserInfo implements UserDetails {
//
//        /**
//         *
//         */
//        private User user;
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return Arrays.asList(new SimpleGrantedAuthority("READER"));
//        }
//
//        @Override
//        public String getPassword() {
//            return this.user.getPassword();
//        }
//
//        @Override
//        public String getUsername() {
//            return this.user.getName();
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return true;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return true;
//        }
//
//        /**  */
//        public User getUser() {
//            return user;
//        }
//
//        /**  */
//        public void setUser(User user) {
//            this.user = user;
//        }
//
//        /**
//         * @param user
//         */
//        UserInfo(final User user) {
//            this.user = user;
//        }
//    }


}
