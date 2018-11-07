package org.wecancodeit.reviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryPopulator implements CommandLineRunner {

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private TagRepository tagRepo;	

	@Override
	public void run(String... args) throws Exception {
		
		Tag buffet = new Tag("Buffet");
		buffet = tagRepo.save(buffet);
		Tag wifi = new Tag("Wifi");
		wifi = tagRepo.save(wifi);
		Tag workout = new Tag("Workout");
		workout = tagRepo.save(workout);
		Tag pool = new Tag("Pool");
		pool = tagRepo.save(pool);
		Tag customerServ = new Tag("Customer Service");
		customerServ = tagRepo.save(customerServ);
	
		
		Review food = new Review("Food", "****", "Variety",buffet,customerServ);
		food = reviewRepo.save(food);

		Review gym = new Review("Gym", "****", "No of days",wifi,pool,customerServ);
		gym = reviewRepo.save(gym);

		Review lakeSide = new Review("Customer Service", "****", "Prompt",buffet,wifi,workout,pool);
		lakeSide = reviewRepo.save(lakeSide);

		Review seaResort = new Review("Ocean View", "****", "Beach Front",buffet,wifi,pool,customerServ);
		seaResort = reviewRepo.save(seaResort);

		Review hilton = new Review("Internet", "*****", "Speed",buffet,workout,pool,wifi);
		hilton = reviewRepo.save(hilton);

		Category lakesideHotel = new Category("Mountain Lodge", "/images/MountainLodge.jpg",
				"Nanyuki, Kenya", food, lakeSide);
		Category mountainHotel = new Category("TreeTops", "/images/TreetopsLodge.jpg", "Nyeri, Kenya",
				food, gym);
		Category plainHotel = new Category("Mara Sopa Lodge", "/images/AmboseliSerena.jpg",
				"Amboseli, Kenya", gym, lakeSide);
		Category beachHotel = new Category("Nyali Beach Hotel", "/images/NyaliBeach.jpg",
				"Mombasa, Kenya", lakeSide, seaResort);
		Category luxuryHotel = new Category("Hilton Hotel", "/images/Hilton.jpg", "New York, USA",
				gym, hilton);

		mountainHotel = categoryRepo.save(mountainHotel);
		plainHotel = categoryRepo.save(plainHotel);
		lakesideHotel = categoryRepo.save(lakesideHotel);
		beachHotel = categoryRepo.save(beachHotel);
		luxuryHotel = categoryRepo.save(luxuryHotel);

	}

}
