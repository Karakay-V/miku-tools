package net.karakaiv.app.network.tool.ping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.karakaiv.app.model.IpV4Address;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    
    private IpV4Address address;

    private Integer bytes;

    private Long time;

    // FIXME: add ttl feature
    // private Integer ttl;

    @Override
    public String toString() {
        if (time != null) {
            return "Reply from " 
                + address.getAddress() 
                + ": bytes=" + bytes
                + " time=" + time + "ms";
        } else {
            return "Request timed out.";
        }
    }
    
}
