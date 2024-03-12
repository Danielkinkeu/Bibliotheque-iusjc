package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Sms;

import java.util.List;

public interface ISms {
    Long saveSms(Sms sms) throws IsjException;
    List<Sms> listSms();
    int deleteSmsByCode(Long code) throws IsjException;
    Long updateSms(Sms sms) throws IsjException;

}
