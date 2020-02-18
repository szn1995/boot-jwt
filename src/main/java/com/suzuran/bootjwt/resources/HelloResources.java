package com.suzuran.bootjwt.resources;

import com.suzuran.bootjwt.authenticate.AuthRequest;
import com.suzuran.bootjwt.authenticate.AuthResponse;
import com.suzuran.bootjwt.securities.MyUserDetailsService;
import com.suzuran.bootjwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author sujan.koju.sastra@gmail.com 18/02/20
 */
@RestController
@RequestMapping("/")
public class HelloResources {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @GetMapping("hello")
    public String getGreeting() {
        return "HELLO WORLD";
    }

    @PostMapping("auths")
    public ResponseEntity authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID CREDENTIALS", e);
        }
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUserName());
        String token = JwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
