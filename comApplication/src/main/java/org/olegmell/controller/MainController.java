package org.olegmell.controller;

import org.olegmell.domain.Request;
import org.olegmell.domain.User;
import org.olegmell.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private RequestRepository repository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Request> requests = repository.findAll();

        if (filter != null && !filter.isEmpty()) {
            requests = repository.findByTag(filter);
        }else {
            requests = repository.findAll();
        }

        model.addAttribute("requests", requests);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model,
            @RequestParam("file")MultipartFile file) throws IOException {

        Request request = new Request(text, tag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));

            request.setFilename(resultFileName);

        }
        repository.save(request);

        Iterable<Request> requests = repository.findAll();

        model.put("requests", requests);

        return "main";
    }
}
