package org.wecancodeit.reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TagController {

	@Resource
	TagRepository tagRepo;
	
	@Resource
	ReviewRepository reviewRepo;
	
	
	@RequestMapping("/add-tag")
	public String addTag(@RequestParam(value="reviewId") long reviewId, String tagName) {
		
		Tag drinks = tagRepo.findByTagName(tagName);
		
		if(drinks == null) {
			drinks = new Tag(tagName);
			tagRepo.save(drinks);
			
		}
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		Tag existing = tagRepo.findByTagName(tagName);
		if(!review.getTags().contains(existing)) {
			review.addTag(drinks);
			reviewRepo.save(review);
			
		};
		return "redirect:/review?id=" + reviewId;
	}
	
	//Can Remove tag with HTML Forms
	@RequestMapping("/remove-tag-button")
	public String removeTagButton(@RequestParam long tagId, @RequestParam Long reviewId) {
		
		Optional<Tag> tagToRemoveResult = tagRepo.findById(tagId);
		
		Tag drinks = tagToRemoveResult.get();
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review food = reviewResult.get();
		
		food.removeTag(drinks);
		reviewRepo.save(food);	
		
		return "redirect:/review?id=" +reviewId;
	}
	
	//Add tags with Java and Thymeleaf
	@RequestMapping("/all-tags-ajax")
	public String showAllTags(Model model) {
		
		model.addAttribute("tags", tagRepo.findAll());
		return "tagsAjax";
		
	}
	
	//Use Ajax to add Tags to the database
	@RequestMapping(path = "/tags/{tagName}",method = RequestMethod.POST)
	public String AddTag(@PathVariable String tagName, Model model) {
		
		Tag tagToAdd = tagRepo.findByTagName(tagName);
		if(tagToAdd == null) {
			tagToAdd = new Tag(tagName);
			tagRepo.save(tagToAdd);
		}
		
		model.addAttribute("tags", tagRepo.findAll());
		return "partials/tags-list-added";	
		
	}
	
	//Use Ajax to remove tags from database
	@RequestMapping(path = "/tags/remove/{tagName}" , method = RequestMethod.POST)
	public String RemoveTag(@PathVariable String tagName, Model model) {
		
		Tag tagToDelete = tagRepo.findByTagName(tagName); 
			if(tagRepo.findByTagName(tagName) != null) {
				for(Review review: tagToDelete.getReviews()) {
					review.removeTag(tagToDelete);
					reviewRepo.save(review);
				}
			}
			
			tagRepo.delete(tagToDelete);
			model.addAttribute("tags", tagRepo.findAll());
			return "partials/tags-list-removed";
	}
	
}
