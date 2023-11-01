package com.auth.authorizationserver.Models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity(name = "customers")
@Table(name = "customers")
public class Customers implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String  customerName;
    @Column(unique = true,nullable = false)
    private String  customerEmail;
    @Column(unique = true)
    private  String username;
    @Column(nullable = false)
    private String customerPassword;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName ="id" )
    ,inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
private List<Roles> roles=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return mapRolesToAuthority(roles);
    }

    @Override
    public String getPassword() {
        return customerPassword;
    }

    @Override
    public String getUsername() {

        return this.customerEmail;
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


       private Collection<GrantedAuthority> mapRolesToAuthority(List<Roles> roles){
       return  roles.stream().map(
               singleRole-> new SimpleGrantedAuthority(singleRole.getName()))
               .collect(Collectors.toList());}
}
