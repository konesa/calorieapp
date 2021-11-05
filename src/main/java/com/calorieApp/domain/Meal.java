package com.calorieApp.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;
	@Column(name = "date", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Date date = java.util.Calendar.getInstance().getTime();
	private String name;
	private int calories;
	private int carbs;
	private int sugar;
	private int protein;

	public Meal() {
	}

	public Meal(String name, int calories, int carbs, int sugar, int protein) {
		this.name = name;
		this.calories = calories;
		this.carbs = carbs;
		this.sugar = sugar;
		this.protein = protein;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
