package br.com.zup.mercado.fake;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FakeUploader {

	public Set<String> send(List<MultipartFile> images) {
		
		return images.stream().map(image -> "https://salva.image.com/"+image.getOriginalFilename())
				.collect(Collectors.toSet());
	}

}
