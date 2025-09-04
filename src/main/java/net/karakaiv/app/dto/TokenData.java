package net.karakaiv.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.karakaiv.app.enums.Tool;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenData {
    
    private String token = "";

    private Tool tool;

    public boolean isExists() {
        return (this.token != null && this.tool != null)
            && (!this.token.isEmpty() && !this.tool.toString().isEmpty()); 
    }
}
