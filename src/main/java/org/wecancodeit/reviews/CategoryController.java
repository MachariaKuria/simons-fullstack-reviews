package org.wecancodeit.reviews;

import java.util.Optional;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

	@Resource
	CategoryRepository categoryRepo;

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	TagRepository tagRepo;

	@RequestMapping("/category")
	public String findOneCategory(@RequestParam(value = "id") long id, Model model) throws CategoryNotFoundException {

		Optional<Category> category = categoryRepo.findById(id);

		if (category.isPresent()) {
			model.addAttribute("categories", category.get());
			return "category";
		}
		throw new CategoryNotFoundException();
	}

	@RequestMapping("/show-categories")
	public String findAllCategories(Model model) {

		model.addAttribute("categories", categoryRepo.findAll());
		return ("categories");
	}

	@RequestMapping("/review")
	public String findOneReview(long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);

		if (review.isPresent()) {
			model.addAttribute("reviews", review.get());
			model.addAttribute("categories", categoryRepo.findByReviewsContains(review.get()));
			return "review";
		}
		throw new ReviewNotFoundException();

	}

	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return ("reviews");

	}

	@RequestMapping("/tag")
	public String findOneTag(long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);

		if (tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			model.addAttribute("reviews", reviewRepo.findByTagsContains(tag.get()));
			return "tag";
		}
		throw new TagNotFoundException();

	}	
	
	@RequestMapping("/show-tags")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return ("tags");

	}

}
