// package com.example.techways.Security;

// import java.util.Collection;
// import java.util.List;
// import java.util.Objects;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.example.techways.Models.User;
// import com.fasterxml.jackson.annotation.JsonIgnore;

// import lombok.Data;
// import lombok.NoArgsConstructor;

// @NoArgsConstructor
// @Data
// public class UserDetailsImpl implements UserDetails {

//     private static final long serialVersionUID = 1L;

//     private Long id;

//     private String username;
//     private String email;

//     @JsonIgnore
//     private String password;

//     private boolean isTwoFactorEnabled;

//     private Collection<? extends GrantedAuthority> authorities;

//     public UserDetailsImpl(Long id, String username, String email, String password, boolean isTwoFactorEnabled,
//             Collection<? extends GrantedAuthority> authorities) {
//         this.id = id;
//         this.username = username;
//         this.email = email;
//         this.password = password;
//         this.isTwoFactorEnabled = isTwoFactorEnabled;
//         this.authorities = authorities;
//     }

//     public static UserDetailsImpl build(User user) {
//         GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().getRoleName().name());
//         return new UserDetailsImpl(
//                 user.getUserId(),
//                 user.getUsername(),
//                 user.getEmail(),
//                 user.getPassword(),
//                 user.isTwoFactorEnabled(),
//                 List.of(authorities));
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return authorities;
//     }

//     public Long getId() {
//         return id;
//     }

//     @Override
//     public String getUsername() {
//         return username;
//     }

//     public String getEmail() {
//         return email;
//     }

//     @Override

//     public String getPassword() {
//         return password;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

//     public boolean isTwoFactorEnabled() {
//         return isTwoFactorEnabled;
//     }

//     @Override
//     public boolean equals(Object o) {
//         if (this == o)
//             return true;
//         if (o == null || getClass() != o.getClass())
//             return false;

//         UserDetailsImpl user = (UserDetailsImpl) o;
//         return Objects.equals(id, user.id);
//     }

//     @Override
//     public int hashCode() {
//         return getClass().hashCode();
//     }

// }
