package org.wecancodeit.reviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String imgUrl;
	private String location;

	@ManyToMany
	private Collection<Review> reviews;


	public Category() {

	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImgUrl() {

		return imgUrl;
	}

	public String getLocation() {
		return location;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	public Category(String name, String imgUrl, String location, Review... reviews) {
		this.name = name;
		this.imgUrl = imgUrl;
		this.location = location;
		this.reviews = new HashSet<>(Arrays.asList(reviews));
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
