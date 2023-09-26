package com.esmc.mobileMoneyCredit.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowPrintStacktrace;

@Data
@AllowPrintStacktrace
@NoArgsConstructor
public class LoginCinetPay {
    private String apikey;
    private String password;
}
