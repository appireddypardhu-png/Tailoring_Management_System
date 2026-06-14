package com.project.Tailoring.Controllers;

import com.project.Tailoring.Service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImageController {

    @Autowired
    private CloudinaryService cloudinaryService;

    /**
     * List all images in a specific folder
     * GET /api/images/folders/{folderName}
     */
    @GetMapping("/folders/{folderName}")
    public ResponseEntity<?> getAllFolders(@PathVariable String folderName) {
        try {
            Map<String, List<Map<String, String>>> data = cloudinaryService.listFoldersWithImages(folderName);
            return ResponseEntity.ok(createSuccess("Folders retrieved", data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createError("Failed to retrieve folders: " + e.getMessage()));
        }
    }


    // Helper methods
    private Map<String, Object> createSuccess(String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

    private Map<String, Object> createError(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        return response;
    }
}

