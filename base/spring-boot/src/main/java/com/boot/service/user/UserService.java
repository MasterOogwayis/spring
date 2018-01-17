package com.boot.service.user;

import org.springframework.stereotype.Service;

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
//        user user = this.userRepository.findByName(username);
//        return Objects.isNull(user) ? null : new UserInfo(user);
//    }
//
//
//    class UserInfo implements UserDetails {
//
//        /**
//         *
//         */
//        private user user;
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
//        public user getUser() {
//            return user;
//        }
//
//        /**  */
//        public void setUser(user user) {
//            this.user = user;
//        }
//
//        /**
//         * @param user
//         */
//        UserInfo(final user user) {
//            this.user = user;
//        }
//    }


}
