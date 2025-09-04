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
import net.karakaiv.app.model.IpV4Address;
import net.karakaiv.app.model.StorageData;
import net.karakaiv.app.request.PingRequest;
import net.karakaiv.app.response.Response;
import net.karakaiv.app.token.TokenService;

@RestController
@RequestMapping(path = "/tool")
public class ToolController {

    @Autowired
    TokenService tokenService;
    
    @GetMapping(path = "/ping")
    public ResponseEntity<Response> ping(@RequestBody PingRequest request) {
        IpV4Address address = new IpV4Address(request.getAddress());
        StorageData data = new StorageData(address, request.getCount(), Tool.PING);

        if (address.isValidIp()) {
            return ResponseEntity.ok(
                new Response(Instant.now().toString(), 
                "token",
                tokenService.generate(data))
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new Response(Instant.now().toString(), 
                "error",
                "Not valid IPv4 address.")
            );
        }
    }
}
