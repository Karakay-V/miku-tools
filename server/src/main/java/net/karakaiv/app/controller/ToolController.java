package net.karakaiv.app.controller;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.karakaiv.app.enums.Tool;
import net.karakaiv.app.model.Address;
import net.karakaiv.app.model.StorageData;
import net.karakaiv.app.request.PingRequest;
import net.karakaiv.app.response.Response;
import net.karakaiv.app.token.TokenService;
import net.karakaiv.app.network.AddressFactory;

@RestController
@RequestMapping(path = "/tool")
public class ToolController {

    @Autowired
    TokenService tokenService;
    
    @GetMapping(path = "/ping")
    public ResponseEntity<Response> ping(@RequestBody PingRequest request) {
        Address address = AddressFactory.from(request.getAddress());

        if (address.isValid()) {
            return ResponseEntity.ok(
                new Response(Instant.now().toString(), 
                "token",
                tokenService.generate(
                    new StorageData(address, request.getCount(), Tool.PING)
                ))
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new Response(Instant.now().toString(), 
                "error",
                "Not valid address.")
            );
        }
    }
}
