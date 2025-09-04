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
public class IpV4Address {
    
    private String address;
    
    private static final String IPV4_REGEX = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";

    private static final Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

    public boolean isValidIp() {
        if (address == null) return false;
        
        Matcher matcher = IPV4_PATTERN.matcher(address);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return isValidIp() ? address : "";
    }

}
