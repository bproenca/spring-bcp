package br.com.bcp;

import br.com.bcp.storage.StorageFileNotFoundException;
import br.com.bcp.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

/*
This class is annotated with @Controller so Spring MVC can pick it up and look for routes.
Each method is tagged with @GetMapping or @PostMapping to tie the path and the HTTP action to a particular Controller action.

In this case:

- GET / looks up the current list of uploaded files from the StorageService and loads it into a Thymeleaf template.
	It calculates a link to the actual resource using MvcUriComponentsBuilder
- GET /files/{filename} loads the resource if it exists, and sends it to the browser to download using a "Content-Disposition" response header
- POST / is geared to handle a multi-part message file and give it to the StorageService for saving
 */
@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
	this.storageService = storageService;
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
		    RedirectAttributes redirectAttributes) {

	storageService.store(file);
	redirectAttributes.addFlashAttribute("message",
			"You successfully uploaded " + file.getOriginalFilename() + "!");

	return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
	return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

	model.addAttribute("files", storageService
			.loadAll()
			.map(path ->
					MvcUriComponentsBuilder
							.fromMethodName(FileUploadController.class, "serveFile",
									path.getFileName().toString())
							.build().toString())
			.collect(Collectors.toList()));

	return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

	Resource file = storageService.loadAsResource(filename);
	return ResponseEntity
			.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
			.body(file);
//	return ResponseEntity
//			.ok()
//			.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
//			.body(file);
    }

}