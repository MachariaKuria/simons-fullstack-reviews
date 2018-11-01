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
	
	

	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long arbitraryCategoryId = 651;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=651")).andExpect(view().name(is("category")));
	}
	
	@Test
	public void shouldBeOkForSingleCourse() throws Exception{
		long arbitraryCategoryId = 111;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=111")).andExpect(status().isOk());
	}


	@Test
	public void shouldNotBeOkForSingleCourse() throws Exception{
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
}
