package net.karakaiv.app.token;

import lombok.extern.slf4j.Slf4j;
import net.karakaiv.app.dto.TokenData;
import net.karakaiv.app.model.StorageData;
import net.karakaiv.app.storage.LocalStorage;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;

@Service
@Slf4j
public class TokenService {

    private final SecureRandom secureRandom = new SecureRandom();

    public String generate(StorageData data) {
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        LocalStorage.put(token, data);
        log.info("New token in cache: " + token);
        return token;
    }

    public boolean validate(String token, String tool) {
        String storedTool = LocalStorage.getIfPresent(token).getTool().toString();
        return storedTool != null && storedTool.equals(tool);
    }

    public TokenData get(String token) {
        return new TokenData(token, LocalStorage.getIfPresent(token).getTool());
    }

    public void remove(String token) {
        log.info("Remove token from cache: " + token);
        LocalStorage.invalidate(token);
    }

}
