package org.wecancodeit.reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByTagsContains(Tag tag);

}
