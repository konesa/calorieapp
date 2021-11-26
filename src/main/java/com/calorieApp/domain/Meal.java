package com.calorieApp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "MEALTABLE")

public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Column(name = "date", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String date = java.time.LocalDate.now().toString();

	@Column(name = "userId", nullable = false, updatable = false)
	private long userId;

	@NotEmpty(message = "Meal name cannot be empty!")
	@Column(name = "mealname", nullable = false)
	private String mealname;

	@Column(name = "calories", nullable = false)
	private int calories;

	@Column(name = "carbs", nullable = false)
	private int carbs;

	@Column(name = "sugar", nullable = false)
	private int sugar;

	@Column(name = "protein", nullable = false)
	private int protein;

	public Meal() {
	}

	public Meal(long id, String date, long userId, @NotEmpty(message = "Meal name cannot be empty!") String mealname,
			int calories, int carbs, int sugar, int protein) {
		super();
		this.id = id;
		this.date = date;
		this.userId = userId;
		this.mealname = mealname;
		this.calories = calories;
		this.carbs = carbs;
		this.sugar = sugar;
		this.protein = protein;
	}

	public Meal(long userId, String mealname, int calories, int carbs, int sugar, int protein) {
		this.userId = userId;
		this.mealname = mealname;
		this.calories = calories;
		this.carbs = carbs;
		this.sugar = sugar;
		this.protein = protein;
	}

	public String getDate() {
		return date;
	}

	public String getMealname() {
		return mealname;
	}

	public void setMealname(String mealname) {
		this.mealname = mealname;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getCarbs() {
		return carbs;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	public int getSugar() {
		return sugar;
	}

	public void setSugar(int sugar) {
		this.sugar = sugar;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Meal [id=" + id + ", date=" + date + ", userId=" + userId + ", mealname=" + mealname + ", calories="
				+ calories + ", carbs=" + carbs + ", sugar=" + sugar + ", protein=" + protein + "]";
	}

}
