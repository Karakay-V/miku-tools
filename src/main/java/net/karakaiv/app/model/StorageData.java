package net.karakaiv.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.karakaiv.app.enums.Tool;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StorageData {

    private IpV4Address address;

    private Integer packetsCount = 300;

    private Tool tool;

}
