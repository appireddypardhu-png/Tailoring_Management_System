package com.project.Tailoring.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Service
public class CloudinaryService {

    @Autowired
    private ObjectProvider<Cloudinary> cloudinaryProvider;

    /**
     * Get the URL for a specific image
     *
     * @param publicId The public ID of the image
     * @return The URL of the image
     */
    public String getImageUrl(String publicId) {
        Cloudinary cloudinary = cloudinaryProvider.getIfAvailable();
        if (cloudinary == null) {
            throw new IllegalStateException("Cloudinary client not available");
        }
        return cloudinary.url()
            .secure(true)
            .generate(publicId);
    }

    /**
     * List all images in a specific folder from Cloudinary.
     * Returns a map with folder name -> list of image entries (public_id, filename, url).
     *
     * @param folderName The Cloudinary folder name to query
     * @return Map of folders with their images
     * @throws Exception if API call fails
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Map<String, List<Map<String, String>>> listFoldersWithImages(String folderName) throws Exception {
        Map<String, List<Map<String, String>>> folders = new HashMap<>();

        Cloudinary cloudinary = cloudinaryProvider.getIfAvailable();
        if (cloudinary == null) {
            throw new IllegalStateException("Cloudinary client not available");
        }

        Map apiResult = cloudinary.api().resourcesByAssetFolder(folderName, ObjectUtils.asMap(
            "max_results", 500
        ));

        List resources = (List) apiResult.get("resources");
        if (resources == null) {
            return folders;
        }

        for (Object obj : resources) {
            Map resource = (Map) obj;
            String publicId = (String) resource.get("public_id");
            String secureUrl = resource.get("secure_url") != null ? resource.get("secure_url").toString() : null;

            // Extract folder and filename from publicId
            String[] parts = publicId.split("/");
            String folderKey = parts.length > 1 ? parts[parts.length - 2] : "root";
            String filename = parts[parts.length - 1];

            Map<String, String> imageEntry = new HashMap<>();
            imageEntry.put("public_id", publicId);
            imageEntry.put("filename", filename);
            imageEntry.put("url", secureUrl != null ? secureUrl : getImageUrl(publicId));

            folders.computeIfAbsent(folderKey, k -> new ArrayList<>()).add(imageEntry);
        }

        return folders;
    }
}

