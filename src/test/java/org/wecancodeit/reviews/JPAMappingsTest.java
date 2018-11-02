package org.wecancodeit.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private CommentRepository commentRepo;

	@Resource
	private TagRepository tagRepo;

	@Test
	public void shouldSaveAndLoadReview() {
		Review review = reviewRepo.save(new Review("Accomodation", "5", "Basis"));
		long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getTitle(), is("Accomodation"));
		assertThat(review.getRating(), is("5"));
		assertThat(review.getBasis(), is("Basis"));

	}

	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review("Accomodation", "Rating", "Basis"));
		long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(reviewId, is(greaterThan(0L)));

	}

	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = new Category("Beach Hotel", "image url", "Nyeri, Kenya");
		category = categoryRepo.save(category);
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getName(), is("Beach Hotel"));
		assertThat(category.getImgUrl(), is("image url"));
		assertThat(category.getLocation(), is("Nyeri, Kenya"));

	}

	@Test
	public void shouldEstablishReviewToCategoryRelationship() {

		Review hotel1 = reviewRepo.save(new Review("Accomodation", "***", "No of Rooms"));
		Review hotel2 = reviewRepo.save(new Review("Accomodation", "***", "No of Rooms"));

		Category category = new Category("Beach Hotel", "image Url", "Mombasa, Kenya", hotel1, hotel2);
		category = categoryRepo.save(category);
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getReviews(), containsInAnyOrder(hotel1, hotel2));

	}

	@Test
	public void shouldFindCategoriesForReview() {
		Review hotel3 = reviewRepo.save(new Review("Rooms", "***", "Cleanliness"));

		Category category1 = categoryRepo.save(new Category("Beach Hotel", "image Url", "Mombasa, Kenya", hotel3));
		Category category2 = categoryRepo.save(new Category("Mountain Hotel", "image Url", "Nyeri, Kenya", hotel3));

		entityManager.flush();
		entityManager.clear();

		Collection<Category> categoriesForReview = categoryRepo.findByReviewsContains(hotel3);
		assertThat(categoriesForReview, containsInAnyOrder(category1, category2));
	}

	@Test
	public void shouldFindCategoryForReviewId() {
		Review hotel4 = reviewRepo.save(new Review("Rooms", "***", "Cleanliness"));
		long reviewId = hotel4.getId();

		Category category1 = categoryRepo.save(new Category("Beach Hotel", "image Url", "Mombasa, Kenya", hotel4));
		Category category2 = categoryRepo.save(new Category("Mountain Hotel", "image Url", "Nyeri, Kenya", hotel4));

		entityManager.flush();
		entityManager.clear();

		Collection<Category> categoriesForReview = categoryRepo.findByReviewsId(reviewId);
		assertThat(categoriesForReview, containsInAnyOrder(category1, category2));

	}

	@Test
	public void shouldSaveAndLoadTag() {
		Tag tag = tagRepo.save(new Tag("Accomodation"));
		long tagId = tag.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();
		assertThat(tag.getTagName(), is("Accomodation"));

	}

	@Test
	public void shouldGenerateTagId() {
		Tag tag = tagRepo.save(new Tag("Accomodation"));
		long tagId = tag.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(tagId, is(greaterThan(0L)));

	}

	@Test
	public void shouldEstablishTagToReviewRelationships() {

		Tag food = tagRepo.save(new Tag("Accomodation"));
		Tag wifi = tagRepo.save(new Tag("Free WiFi"));

		Review review1 = reviewRepo.save(new Review("Accomodation", "***", "No of Rooms", food, wifi));

		review1 = reviewRepo.save(review1);
		long reviewId = review1.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review1 = result.get();
		assertThat(review1.getTags(), containsInAnyOrder(food, wifi));

	}

	@Test
	public void shouldHaveOneCommentOnOneReview() {

		Tag buffet = tagRepo.save(new Tag("Buffet"));

		Review food = new Review("Food", "****", "Variety", buffet);
		food = reviewRepo.save(food);


		long reviewId = food.getId();

		Comment testComment1 = new Comment("Mashaa", food, "Tasty");
		testComment1 = commentRepo.save(testComment1);
		long testCommentId = testComment1.getId();
		
		entityManager.flush();
		entityManager.clear();

		Optional<Comment> testCommentResult = commentRepo.findById(testCommentId);
		testComment1 = testCommentResult.get();
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		food = reviewResult.get();

		assertThat(testComment1.getAuthor(), is("Mashaa"));
		assertThat(testComment1.getReview(), is(food));
		assertThat(testComment1.getContent(), is("Tasty"));
	}
		
	@Test
	public void shouldHaveAnotherCommentOnOneReview() {

		Tag buffet = tagRepo.save(new Tag("Buffet"));

		Review food = new Review("Food", "****", "Variety", buffet);
		food = reviewRepo.save(food);


		long reviewId = food.getId();
		
		Comment testComment2 = new Comment("Manga", food, "Whack");
		testComment2 = commentRepo.save(testComment2);
		long testComment2Id = testComment2.getId();

		entityManager.flush();
		entityManager.clear();
		
		Optional<Comment> testComment2Result = commentRepo.findById(testComment2Id);
		testComment2 = testComment2Result.get();		
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		food = reviewResult.get();
		
		assertThat(testComment2.getAuthor(), is("Manga"));
		assertThat(testComment2.getReview(), is(food));
		assertThat(testComment2.getContent(), is("Whack"));
		
	}
	
	@Test
	public void shouldHaveAllCommentsForReview() {
		Tag buffet = tagRepo.save(new Tag("Buffet"));

		Review food = new Review("Food", "****", "Variety", buffet);
		food = reviewRepo.save(food);


		long reviewId = food.getId();		
		Comment testComment1 = new Comment("Mashaa", food, "Tasty");
		testComment1 = commentRepo.save(testComment1);
		long testCommentId = testComment1.getId();
		
		Comment testComment2 = new Comment("Manga", food, "Whack");
		testComment2 = commentRepo.save(testComment2);
		long testComment2Id = testComment2.getId();

		entityManager.flush();
		entityManager.clear();
		
		Optional<Comment> testCommentResult = commentRepo.findById(testCommentId);
		testComment1 = testCommentResult.get();		
		
		Optional<Comment> testComment2Result = commentRepo.findById(testComment2Id);
		testComment2 = testComment2Result.get();		
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		food = reviewResult.get();
			
		assertThat(food.getComments(), containsInAnyOrder(testComment2,testComment1));
	}

}
