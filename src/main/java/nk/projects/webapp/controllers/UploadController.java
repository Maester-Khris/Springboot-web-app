package nk.projects.webapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("/upload")
@Controller
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @GetMapping
    public String showForm(){
        return "upload_form";
    }
 
    @PostMapping("/doc")
    public String postMethodName(Model model, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String message = "";
        if (file.isEmpty()) {
            logger.info("file is empty");
            message = "Please select a file";
            model.addAttribute("message", message);
            return "upload_form";
        }
        try {
            System.out.println("processing the upload of the file");
            message = "File successfully uploaded: " + file.getOriginalFilename();
            model.addAttribute("message", message);
        } catch (Exception exception) {
            message = "An error occurred while uploading the file: " + file.getOriginalFilename() + ". Error: " + exception.getMessage();
            model.addAttribute("message", message);
        }
        return "upload_form";
    }
   
}

