package br.com.zup.mercado.form;

import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.ProductImages;

public class ImagesForm {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> images;


	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}

	public List<MultipartFile> getImages() {
		return images;
	}
	
	public ProductImages toModel(String image, Product product) {
		return new ProductImages(image, product);
	}
	
}
