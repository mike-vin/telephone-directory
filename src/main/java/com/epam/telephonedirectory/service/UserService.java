package com.epam.telephonedirectory.service;


import com.epam.telephonedirectory.domain.User;
import com.itextpdf.text.Document;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public interface UserService extends BaseCrudService<User, Long> {
    ByteArrayInputStream findAllInPDF();
}
