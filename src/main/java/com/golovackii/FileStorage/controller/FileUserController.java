package com.golovackii.FileStorage.controller;

import com.golovackii.FileStorage.model.Event;
import com.golovackii.FileStorage.model.FileUser;
import com.golovackii.FileStorage.model.User;
import com.golovackii.FileStorage.service.EventService;
import com.golovackii.FileStorage.service.FileUserService;
import com.golovackii.FileStorage.service.UserService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileUserController {

    @Autowired
    private EventService eventService;

    @Autowired
    private FileUserService fileUserService;

    @Autowired
    private UserService userService;

    @Value("${path.file}")
    private String filePath;

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable("id") Long fileId) {

        FileUser fileUser = fileUserService.get(fileId);

        if (fileUser == null) {
            return null;
        }
        
        String filePath = fileUser.getFileLocation() + fileUser.getFileName();

        File file = new File(filePath);
        
        Resource fileResource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(fileResource);
    }

    @GetMapping
    public List<FileUser> getUserFiles() {
        return fileUserService.getAll();
    }

    @PostMapping
    public void saveFile(@RequestHeader("user-id") long userId,
            @RequestParam("file") MultipartFile file) {
        User user = userService.get(userId);

        if (user == null) {
            return;
        }

        if (!file.isEmpty()) {
            try (BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())))) {

                byte[] bytes = file.getBytes();
                stream.write(bytes);

                FileUser fileUser = new FileUser(file.getOriginalFilename(), filePath);
                fileUserService.save(fileUser);

                Event event = new Event("user uploaded file", user, fileUser);
                eventService.save(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @PutMapping
    public void updateFile(@RequestHeader("user-id") long userId,
            @RequestParam("file") MultipartFile file) {

        User user = userService.get(userId);

        if (user == null) {
            return;
        }

        FileUser fileUser = user.getEvents().stream()
                .filter(o -> o.getFileUser().getFileName().equals(file.getOriginalFilename()))
                .findFirst()
                .orElse(null)
                .getFileUser();

        if (fileUser == null) {
            return;
        }

        if (!file.isEmpty()) {
            try (BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())))) {

                byte[] bytes = file.getBytes();
                stream.write(bytes);

                Event event = new Event("user updated file", user, fileUser);
                eventService.save(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@RequestHeader("user-id") long userId,
            @PathVariable("id") long fileId) {

        User user = userService.get(userId);

        if (user == null) {
            return;
        }

        FileUser fileUser = fileUserService.get(fileId);
        
        if (fileUser == null) {
            return;
        }
        
        File file = new File(fileUser.getFileLocation() + fileUser.getFileName());

        if (file.delete()) {
            eventService.save(new Event("file was deleted", user, fileUser));
        }
    }
}
