package com.klass.lab01.services;

import com.klass.lab01.domain.Role;
import com.klass.lab01.domain.User;
import com.klass.lab01.dto.LocalUserDetail;
import com.klass.lab01.dto.request.LoginBody;
import com.klass.lab01.dto.request.RegisterBody;
import com.klass.lab01.dto.response.AuthResponse;
import com.klass.lab01.repository.RoleRepository;
import com.klass.lab01.repository.UserRepository;
import com.klass.lab01.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterBody registerBody) {
        var user = new User();
        user.setName(registerBody.getName());
        user.setPassword(passwordEncoder.encode(registerBody.getPassword()));
        user.setEmail(registerBody.getEmail());
        var role = roleRepository.findByName(registerBody.getRole()).get();
        user.addRole(role);
        role.getUser().add(user);
        userRepository.save(user);
        LocalUserDetail userDetail = new LocalUserDetail(user);
        var jwt = jwtUtil.generateToken(userDetail);
        var resp = new AuthResponse();
        resp.setToken(jwt);
        return resp;
    }
    public AuthResponse login(LoginBody loginBody) {
        System.out.println("is this code running0: " + loginBody.getEmail());
       try{
           authenticationManager
                   .authenticate(new UsernamePasswordAuthenticationToken(
                           loginBody.getEmail(),loginBody.getPassword()
                   ));
       }catch (Exception e) {
           System.out.println("Exception: " + e);
       }
        var user = userRepository.findByEmail(loginBody.getEmail()).get();
        System.out.println("is this code running: " + user.getRoles());

        LocalUserDetail userDetail = new LocalUserDetail(user);
        var jwt = jwtUtil.generateToken(userDetail);
        var resp = new AuthResponse();
        resp.setToken(jwt);
        return resp;

    }
}
