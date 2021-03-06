package com.epam.telephonedirectory.controller;

import com.epam.telephonedirectory.domain.GSMProvider;
import com.epam.telephonedirectory.domain.Telephone;
import com.epam.telephonedirectory.domain.User;
import com.epam.telephonedirectory.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserMvcController {
    private UserService service;

    @GetMapping("create")
    public String createPage() {
        return "createUser";
    }

    @GetMapping("create/json")
    public String batchCreatePage() {
        return "jsonUpload";
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute("user") User user,
                               @RequestBody MultiValueMap<String, String> formData) {
        log.info("MVC-> formData - {}", formData);
        user.setPhoneNumbers(extractPhones(formData));

        User result = service.insert(user);

        ModelAndView modelAndView = new ModelAndView("userOperationResult");
        modelAndView.setStatus(HttpStatus.CREATED);
        modelAndView.addObject("message", "New user created:");
        modelAndView.addObject("entity", result);
        return modelAndView;
    }

    @PostMapping("create/json")
    public ModelAndView batchCreate(@RequestParam("file") MultipartFile jsonFile) {
        requireValidFile(jsonFile);
        log.info("batch Create User JSON file-'{}', Size-'{}'", jsonFile.getOriginalFilename(), jsonFile.getSize());

        service.insert(jsonFile);
        return findAll();
    }

    private void requireValidFile(MultipartFile jsonFile) {
        Objects.requireNonNull(jsonFile, "Invalid file");

        String fileName = jsonFile.getOriginalFilename();
        if (isNotEmpty(fileName) && fileName.substring(fileName.lastIndexOf('.')).equalsIgnoreCase(".json")) {
            return;
        }
        throw new IllegalArgumentException("Invalid file type. Must be '.json' extension!");
    }

    private Set<Telephone> extractPhones(MultiValueMap<String, String> formData) {
        Set<Telephone> phones = new HashSet<>(2);

        Optional<Telephone> primary = mapToPhone(formData.getFirst("primaryPhone"), formData.getFirst("primaryPhoneCompany"));
        Optional<Telephone> additional = mapToPhone(formData.getFirst("additionalPhone"), formData.getFirst("additionalPhoneCompany"));

        primary.ifPresent(phones::add);
        additional.ifPresent(phones::add);
        return phones;
    }

    private Optional<Telephone> mapToPhone(String phone, String phoneCompany) {
        final GSMProvider primaryProvider = Optional.ofNullable(phoneCompany)
                .map(String::valueOf)
                .map(company -> {
                    GSMProvider provider = new GSMProvider();
                    provider.setName(company);
                    return provider;
                })
                .orElse(null);
        return Optional.ofNullable(phone)
                .map(String::valueOf)
                .map(p -> {
                    Telephone telephone = new Telephone();
                    telephone.setNumber(p);
                    telephone.setCompany(primaryProvider);
                    return telephone;
                });
    }


    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable(name = "id") Long id) {
        log.info("MVC-> delete User: id = '{}'", id);
        service.delete(id);
        return findAll();
    }

    @PatchMapping
    public ModelAndView edit(@RequestBody User user) {
        log.info("MVC-> edit User: {}", user.toString());
        service.update(user);
        return findAll();

    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable(name = "id") Long id) {
        log.info("MVC-> Find User By Id  ='{}'", id);

        User user = service.findById(id);

        ModelAndView modelAndView = new ModelAndView("userOperationResult");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("message", "User found:");
        modelAndView.addObject("entity", user);
        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView findAll() {
        log.info("MVC-> findAll Users");
        Collection<User> users = service.findAll();
        ModelAndView modelAndView = new ModelAndView("userList");
        modelAndView.addObject("entityList", users);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

}
