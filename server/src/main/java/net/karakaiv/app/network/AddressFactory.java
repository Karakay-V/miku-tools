package net.karakaiv.app.network;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.karakaiv.app.model.Address;
import net.karakaiv.app.model.DomainAddress;
import net.karakaiv.app.model.IpV4Address;

public class AddressFactory {
    
    public static final String IPV4_REGEX = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";

    public static final Pattern IPV4_PATTERN = Pattern.compile(AddressFactory.IPV4_REGEX);

    public static final String DOMAIN_REGEX = "^(?!-)([\\p{L}\\p{N}-]{1,63}(?<!-)\\.)+[\\p{L}]{2,}$";
    
    public static final Pattern DOMAIN_PATTERN = Pattern.compile(DOMAIN_REGEX);

    public static Address from(String address) {
        if (address == null) return null;

        if (DOMAIN_PATTERN.matcher(address).matches()) {
            return new DomainAddress(address);
        } else if (IPV4_PATTERN.matcher(address).matches()) {
            return new IpV4Address(address);
        } else return null;
    }
    
}