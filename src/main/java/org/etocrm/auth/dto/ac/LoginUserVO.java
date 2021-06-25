package org.etocrm.auth.dto.ac;

import lombok.Data;
import org.etocrm.auth.dto.TokenValue;

@Data
public class LoginUserVO extends TokenValue {

    private Long id;
}
