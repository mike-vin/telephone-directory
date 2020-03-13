package com.epam.telephonedirectory.controller;

import com.epam.telephonedirectory.domain.User;
import com.epam.telephonedirectory.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.Collection;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService service;

    @PostMapping
    public User create(@RequestBody User user) {
        log.debug("create User: {}", user.toString());
        return service.insert(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        log.debug("delete User: id = '{}'", id);
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping
    public ResponseEntity<User> edit(@RequestBody User user) {
        log.debug("edit User: {}", user.toString());
        service.update(user);
        return ResponseEntity.accepted().body(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable(name = "id") Long id) {
        log.debug("Find User By Id  ='{}'", id);
        return service.findById(id);
    }

    @GetMapping("/all")
    public Collection<User> findAll() {
        log.debug("findAll Users");
        return service.findAll();
    }


    @GetMapping(value = "/all/pdf")
    public ResponseEntity<?> downloadAllPDF() {
        log.debug("download All Users .PDF");

        ByteArrayInputStream allInPDF = service.findAllInPDF();

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(allInPDF));
    }

}
