package com.core.ecommanager.controller;
import com.core.ecommanager.config.JwtTokenUtil;
import com.core.ecommanager.model.JwtRequest;
import com.core.ecommanager.model.JwtResponse;
import com.core.ecommanager.model.UserDto;
import com.core.ecommanager.service.JwtUserDetailsService;
import com.core.ecommanager.utilityFunction.ApplicationAESCrypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	ApplicationAESCrypto aes = new ApplicationAESCrypto();

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

//		System.out.println("username: "+authenticationRequest.getUsername());
//		System.out.println("password: "+authenticationRequest.getPassword());
//		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = null;
		if(userDetails.getPassword().equals(aes.encrypt(authenticationRequest.getPassword()))){
			token = jwtTokenUtil.generateToken(userDetails);
		}
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	private void authenticateAES(String username, String password){
//		String actualPass = userDetailsService.loadUserByUsername();
	}
}
