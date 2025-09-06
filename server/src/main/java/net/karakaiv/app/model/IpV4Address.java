package net.karakaiv.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.karakaiv.app.network.AddressFactory;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IpV4Address implements Address {
    
    private String address;
    
    @Override
    public boolean isValid() {
        if (address == null) return false;
        return AddressFactory.IPV4_PATTERN.matcher(address).matches();
    }

    @Override
    public String toString() {
        return isValid() ? address : "";
    }

}
