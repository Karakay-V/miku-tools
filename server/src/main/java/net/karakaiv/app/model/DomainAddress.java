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
public class DomainAddress implements Address {

    private String domain;
    
    @Override
    public boolean isValid() {
        if (domain == null) return false;
        return AddressFactory.DOMAIN_PATTERN.matcher(domain).matches();
    }

    @Override
    public String toString() {
        return isValid() ? domain : "";
    }
}
