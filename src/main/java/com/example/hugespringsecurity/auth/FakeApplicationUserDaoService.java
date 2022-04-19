package com.example.hugespringsecurity.auth;

import com.example.hugespringsecurity.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.hugespringsecurity.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDAO{
    private final PasswordEncoder passwordEncoder;

    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUser().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUser(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "anna",
                        passwordEncoder.encode("student"),
                        STUDENT.getGrantedAuthority(),
                        true, true,true,true
                ),
                new ApplicationUser(
                        "linda",
                        passwordEncoder.encode("admin"),
                        ADMIN.getGrantedAuthority(),
                        true, true,true,true
                ),
                new ApplicationUser(
                        "tom",
                        passwordEncoder.encode("admin"),
                        ADMIN_TRAINEE.getGrantedAuthority(),
                        true, true,true,true
                )
        );
        return applicationUsers;
    }
}
