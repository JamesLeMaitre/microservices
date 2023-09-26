package com.esmc.demandeAchatBanKsu.servicesInterface;

import com.esmc.demandeAchatBanKsu.entities.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
