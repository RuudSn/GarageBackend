package nl.ruud.Eindopdracht.controller;

import nl.ruud.Eindopdracht.dto.payload.AuthenticationRequest;
import nl.ruud.Eindopdracht.dto.payload.AuthenticationResponse;
import nl.ruud.Eindopdracht.exception.UserNotFoundException;
import nl.ruud.Eindopdracht.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


    UserAuthenticationService userAuthenticationService;

    @Autowired
    public AuthenticationController(UserAuthenticationService userAuthenticationService){
        this.userAuthenticationService = userAuthenticationService;
    }



    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        AuthenticationResponse authenticationResponse = userAuthenticationService.authenticateUser(username, password);

        return ResponseEntity.ok(authenticationResponse);
    }




}
