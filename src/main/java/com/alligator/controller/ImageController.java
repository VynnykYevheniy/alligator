package com.alligator.controller;

import com.alligator.model.Image;
import com.alligator.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/image")
public class ImageController {
	private final ImageService imageService;

	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
		try {
			String mediaType = file.getContentType();
			byte[] content = file.getBytes();
			Image savedImage = imageService.save(new Image(null, content, mediaType));
			return savedImage == null
					? ResponseEntity.internalServerError().build()
					: ResponseEntity.ok().build();
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<byte[]> getImageById(@PathVariable("id") Long id) {
		return imageService.findById(id)
				.map(image -> ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(image.getMediaType()))
						.body(image.getContent()))
				.orElse(ResponseEntity.notFound().build());
	}
}