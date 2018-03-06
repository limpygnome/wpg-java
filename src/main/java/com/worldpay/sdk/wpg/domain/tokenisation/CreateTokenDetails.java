package com.worldpay.sdk.wpg.domain.tokenisation;

import java.util.Date;

public class CreateTokenDetails
{
    private TokenScope scope;
    private String eventReference;
    private String reason;
    private int shortLifeMins;
    private Date expiry;
}
