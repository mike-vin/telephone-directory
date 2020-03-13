package com.epam.telephonedirectory.controller;

import com.epam.telephonedirectory.domain.User;
import com.epam.telephonedirectory.exception.PdfCreationException;
import com.epam.telephonedirectory.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private UserService service;

    @PostMapping
    public User create(@RequestBody User user) {
        log.info("create User: {}", user.toString());
        return service.insert(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        log.info("delete User: id = '{}'", id);
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable(name = "id") Long id) {
        log.info("Find User By Id  ='{}'", id);
        return service.findById(id);
    }

    @GetMapping("/all")
    public Collection<User> findAll() {
        log.info("findAll Users");
        return service.findAll();
    }


    @GetMapping(value = "/all/pdf")
    public ResponseEntity<?> downloadAllPDF() {
        log.info("download All Users .PDF");

        ByteArrayInputStream allInPDF = service.findAllInPDF();

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(allInPDF));
    }

}
