package com.epam.telephonedirectory.service.impl;

import com.epam.telephonedirectory.domain.User;
import com.epam.telephonedirectory.exception.JsonFileMappingException;
import com.epam.telephonedirectory.exception.NotFoundException;
import com.epam.telephonedirectory.exception.PdfCreationException;
import com.epam.telephonedirectory.repository.UserRepository;
import com.epam.telephonedirectory.service.UserService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND = "User with id='%d' not found";

    private ObjectMapper objectMapper;
    private UserRepository repository;

    @Override
    public User insert(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(notFoundException(id));
    }

    private Supplier<NotFoundException> notFoundException(Long id) {
        String errMessage = String.format(USER_NOT_FOUND, id);
        return () -> new NotFoundException(errMessage);
    }

    @Override
    public Collection<User> findAll() {
        return repository.findAll();
    }

    @Override
    public ByteArrayInputStream findAllInPDF() {
        List<User> userList = repository.findAll();
        Document document = new Document();
        com.itextpdf.text.List list = new com.itextpdf.text.List();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();
            for (User user : userList) {
                String userString = user.toString();
                list.add(userString);
            }
            document.add(new Paragraph("Users"));
            document.add(list);
            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            throw new PdfCreationException(e.getMessage());
        }

    }

    @Override
    public void insert(MultipartFile jsonFile) {
        try {
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, User.class);
            List<User> users = objectMapper.readValue(jsonFile.getInputStream(), collectionType);
            repository.saveAll(users);
        } catch (IOException e) {
            throw new JsonFileMappingException(e.getMessage());
        }
    }
}
