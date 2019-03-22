package com.anka.traveltogether.user.security;

import com.anka.traveltogether.user.User;
import com.anka.traveltogether.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class UserDetailsService implements ReactiveUserDetailsService {

    private final UserRepository users;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return users.findByEmail(username).map(CustomUser::new);
    }

    private class CustomUser extends User implements UserDetails {

        public CustomUser(User user) {
            super(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }

        @Override
        public String getUsername() {
            return this.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
