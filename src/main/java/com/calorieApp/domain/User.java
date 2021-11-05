package com.calorieApp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERTABLE", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
@PasswordMatcher
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;

	@Email(message = "Invalid email!")
	@NotEmpty(message = "Email cannot be empty!")
	@Column(name = "email", nullable = false)
	private String email;

	@NotEmpty(message = "First name cannot be empty!")
	@Column(name = "firstName", nullable = false)
	private String firstName;

	@NotEmpty(message = "Last name cannot be empty!")
	@Column(name = "lastName", nullable = false)
	private String lastName;

	@Size(min = 6, message = "Too short password!")
	@NotEmpty(message = "Password cannot be empty!")
	@Column(name = "password", nullable = false)
	private String password;

	@Transient
	private String matchingPassword;

	@Column(name = "role", nullable = false)
	private String role;

	public User() {
	}

	public User(String firstName, String lastName, String email, String password, String password2,
			String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.matchingPassword = password2;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", matchingPassword=" + matchingPassword + ", role=" + role + "]";
	}

}