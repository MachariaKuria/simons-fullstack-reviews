package org.wecancodeit.reviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	private String title;
	private String rating;
	private String basis;

	@ManyToMany(mappedBy = "reviews")
	private Collection<Category> categories;
	
	@OneToMany(mappedBy="review")
	private Collection<Comment> comments;
	
	@ManyToMany
	private Collection<Tag> tags;

	public long getId() {

		return id;
	}

	public String getTitle() {

		return title;
	}

	public String getRating() {

		return rating;
	}

	public String getBasis() {

		return basis;
	}

	public Collection<Category> getCategories() {
		return categories;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public Review() {

	}

	public Collection<Tag> getTags() {
		return tags;
	}

	// allowing the collection to add this tag we created in our form.
	public void addTag(Tag newTag) {
		tags.add(newTag);

	}

	// allowing the collection to have a tag removed.
	public void removeTag(Tag tagToRemove) {
		tags.remove(tagToRemove);

	}

	public Review(String title, String rating, String basis, Tag...tags) {
		this.title = title;
		this.rating = rating;
		this.basis = basis;
		this.tags = new HashSet<>( Arrays.asList(tags));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}



}
