package kr.co.yoon.product;

import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class product_controller {
	
	PrintWriter pw = null;
	
	@GetMapping("/product.do")
	public String product (Model m) {
		String product = "냉장고";
		m.addAttribute("product", product);
		return null;
	}
}
