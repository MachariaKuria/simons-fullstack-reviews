package org.wecancodeit.reviews;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByTagName(String tagName);//query your tags to see if they exist.

}
