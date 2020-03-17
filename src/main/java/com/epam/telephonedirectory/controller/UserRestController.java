package com.epam.telephonedirectory.controller;

import com.epam.telephonedirectory.domain.User;
import com.epam.telephonedirectory.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Objects;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("rest/user/")
public class UserRestController {
    private UserService service;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("create User: {}", user.toString());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.insert(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        log.info("delete User: id = '{}'", id);
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping
    public ResponseEntity<User> edit(@RequestBody User user) {
        log.info("edit User: {}", user.toString());
        return ResponseEntity
                .accepted()
                .body(service.update(user));
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
