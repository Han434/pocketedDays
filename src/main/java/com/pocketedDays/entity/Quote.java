package com.pocketedDays.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * The type Quote.
 */
public class Quote {

	@JsonProperty("quote")
	private String quote;

	@JsonProperty("author")
	private String author;

	@JsonProperty("id")
	private int id;

	/**
	 * Set quote.
	 *
	 * @param quote the quote
	 */
	public void setQuote(String quote){
		this.quote = quote;
	}

	/**
	 * Get quote string.
	 *
	 * @return the string
	 */
	public String getQuote(){
		return quote;
	}

	/**
	 * Set author.
	 *
	 * @param author the author
	 */
	public void setAuthor(String author){
		this.author = author;
	}

	/**
	 * Get author string.
	 *
	 * @return the string
	 */
	public String getAuthor(){
		return author;
	}

	/**
	 * Set id.
	 *
	 * @param id the id
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Get id int.
	 *
	 * @return the int
	 */
	public int getId(){
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Quote)) return false;
		Quote quote1 = (Quote) o;
		return getId() == quote1.getId() && Objects.equals(getQuote(), quote1.getQuote()) && Objects.equals(getAuthor(), quote1.getAuthor());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getQuote(), getAuthor(), getId());
	}

	@Override
 	public String toString(){
		StringBuilder quote = new StringBuilder("Quote{");
		quote.append("quote='").append(quote);
		quote.append("', author='").append(author);
		quote.append("', id='").append(id);
		quote.append("'}");
		return quote.toString();
	}
}