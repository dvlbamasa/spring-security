package com.exist.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exist.model.Person;
import com.exist.model.Role;
 
@Service("userDetailsService")
public class UserDetailsServiceImplementation implements UserDetailsService {
 
    @Autowired
    private PersonService personService;
     
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.getPersonByUserName(username);
        if (person==null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(person.getUsername(), person.getPassword(), 
             true, true, true, true, getGrantedAuthorities(person));
    }
     
    private List<GrantedAuthority> getGrantedAuthorities(Person person){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : person.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    } 
}