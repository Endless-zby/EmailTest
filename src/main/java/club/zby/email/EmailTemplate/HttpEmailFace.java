package club.zby.email.EmailTemplate;

import club.zby.email.Until.Result;

public interface HttpEmailFace {

    Result httpEmail(String id, String email);

}
