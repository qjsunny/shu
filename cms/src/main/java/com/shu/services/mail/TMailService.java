package com.shu.services.mail;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.mail.TMail;

import java.util.List;

/**
 * Created by admin on 2017/2/2.
 */
public interface TMailService {
    public List<TMail> getMailListByParam(TMail mail, Order order, Pager page);

    public void addMail(TMail mail);

    public void modifyMail(TMail mail);

    public TMail getMailById(String id);
}
