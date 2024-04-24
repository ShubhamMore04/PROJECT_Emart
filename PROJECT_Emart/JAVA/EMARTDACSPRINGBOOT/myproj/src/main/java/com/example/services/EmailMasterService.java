package com.example.services;

import com.example.entities.EmailMaster;

public interface EmailMasterService {
    String sendSimpleMail(EmailMaster details);

    String sendMailWithAttachment(EmailMaster details);

    // New method for sending email with PDF attachment
    String sendMailWithPDFAttachment(EmailMaster details);
}
