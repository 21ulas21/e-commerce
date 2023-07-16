package org.bitirme.paymentservice.payment.impl;

public enum PaymentStatus {
    Beklemede (1) ,
    Hazirlaniyor (2),
    Kargoda(3),
    Teslim_edildi(4);


    PaymentStatus(int i) {
    }
}
