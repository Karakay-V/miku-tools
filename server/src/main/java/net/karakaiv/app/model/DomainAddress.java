package net.karakaiv.app.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DomainAddress implements Address {

    private String domain;
    
    private static final String DOMAIN_REGEX = "^(?!-)([\\p{L}\\p{N}-]{1,63}(?<!-)\\.)+[\\p{L}]{2,}$";

    private static final Pattern DOMAIN_PATTERN = Pattern.compile(DOMAIN_REGEX);

    @Override
    public boolean isValid() {
        if (domain == null) return false;
        
        Matcher matcher = DOMAIN_PATTERN.matcher(domain);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return isValid() ? domain : "";
    }
}
