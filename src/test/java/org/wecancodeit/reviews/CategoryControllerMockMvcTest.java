package org.wecancodeit.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@Mock
	private Category category;

	@Mock
	private Category anotherCategory;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;	
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@Mock
	private Tag tag;
	
	@Mock
	private Tag anotherTag;	
	
	@MockBean
	private TagRepository tagRepo;	

	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long arbitraryCategoryId = 651;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=651")).andExpect(view().name(is("category")));
	}
	
	@Test
	public void shouldBeOkForSingleCategory() throws Exception{
		long arbitraryCategoryId = 111;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=111")).andExpect(status().isOk());
	}


	@Test
	public void shouldNotBeOkForSingleCategory() throws Exception{
		mvc.perform(get("/category?id=45")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleCategoryIntoModel() throws Exception {
		
		when(categoryRepo.findById(111L)).thenReturn(Optional.of(category));
		
		mvc.perform(get("/category?id=111")).andExpect(model().attribute("categories", is(category)));
	}
	
	@Test
	public void shouldRouteToAllCategoriesView() throws Exception {
		
		mvc.perform(get("/show-categories")).andExpect(view().name(is("categories")));
	}
	
	@Test
	public void shouldBeOkForAllCategories() throws Exception {
		mvc.perform(get("/show-categories")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllCategoriesIntoModel() throws Exception{
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		mvc.perform(get("/show-categories")).andExpect(model().attribute("categories", is(allCategories)));
	}
	
	
	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long arbitraryReviewId=34;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=34")).andExpect(view().name(is("review")));
	}
	
	@Test
	public void shouldBeOkForSingleReview() throws Exception{
		long arbitraryCategoryId = 109;
		when(reviewRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=109")).andExpect(status().isOk());
	}


	@Test
	public void shouldNotBeOkForSingleReview() throws Exception{
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleReviewIntoModel() throws Exception {
		
		when(reviewRepo.findById(111L)).thenReturn(Optional.of(review));
		
		mvc.perform(get("/review?id=111")).andExpect(model().attribute("reviews", is(review)));
	}
	
	@Test
	public void shouldRouteToAllReviewsView() throws Exception {
		
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllreviewsIntoModel() throws Exception{
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
	
	@Test
	public void shouldRouteToSingleTagView() throws Exception {
		long arbitraryTagId=7;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=7")).andExpect(view().name(is("tag")));
	}
	
	@Test
	public void shouldBeOkForSingleTag() throws Exception{
		long arbitraryTagId = 111;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=111")).andExpect(status().isOk());
	}	
	
	@Test
	public void shouldNotBeOkForSingleTag() throws Exception{
		mvc.perform(get("/tag?id=45")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleTagIntoModel() throws Exception {
		
		when(tagRepo.findById(111L)).thenReturn(Optional.of(tag));
		
		mvc.perform(get("/tag?id=111")).andExpect(model().attribute("tags", is(tag)));
	}
	
	@Test
	public void shouldRouteToAllTagsView() throws Exception {
		
		mvc.perform(get("/show-tags")).andExpect(view().name(is("tags")));
	}
	
	@Test
	public void shouldBeOkForAllTags() throws Exception {
		mvc.perform(get("/show-tags")).andExpect(status().isOk());
	}
	/*
	@Test
	public void shouldPutAllreviewsIntoModel() throws Exception{
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
	
	*/
	
	
	
	
}
