package org.wecancodeit.reviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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

	public Review() {

	}

	public Review(String title, String rating, String basis) {
		this.title = title;
		this.rating = rating;
		this.basis = basis;
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
