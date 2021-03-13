package com.bae.starterproject.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {

	private RemoteWebDriver driver;

	@BeforeEach
	void setUp() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
	}

	@Test
	void seleniumCreateTest() {
		this.driver.get("http://127.0.0.1:5500/");

		WebElement titleInput = this.driver.findElement(By.xpath("//*[@id=\"book-title\"]"));

		titleInput.sendKeys("Java for Dummies");

		WebElement authorInput = this.driver.findElement(By.xpath("//*[@id=\"book-author\"]"));

		authorInput.sendKeys("Barry Burd");

		WebElement genreInput = this.driver.findElement(By.xpath("//*[@id=\"book-genre\"]"));

		genreInput.sendKeys("Computer Programming");

		genreInput.sendKeys(Keys.ENTER);

		WebElement form = this.driver.findElement(By.xpath("/html"));

		Assertions.assertTrue(form.getText().contains("Success!"));

	}

	@Test
	void seleniumReadTest() {
		this.driver.get("http://127.0.0.1:5500/");

		WebElement card = this.driver.findElement(By.xpath("//*[@id=\"output\"]"));

		Assertions.assertTrue(card.getText().contains("Edit"));
	}

	@Test
	void seleniumUpdateTest() {
		this.driver.get("http://127.0.0.1:5500/");

		WebElement editButton = this.driver.findElement(By.xpath("//*[@id=\"output\"]/div[1]/div[2]/button[1]"));

		editButton.click();

		WebElement modalTitle = this.driver.findElement(By.xpath("//*[@id=\"modal-title\"]"));

		modalTitle.clear();

		modalTitle.sendKeys("Java for a dummy");

		WebElement modalAuthor = this.driver.findElement(By.xpath("//*[@id=\"modal-title\"]"));

		modalAuthor.sendKeys("Barry Burd");

		WebElement modalGenre = this.driver.findElement(By.xpath("//*[@id=\"modal-title\"]"));

		modalGenre.sendKeys("Computer Programming");

		modalGenre.sendKeys(Keys.ENTER);

		WebElement closeModal = this.driver.findElement(By.xpath("//*[@id=\"modal-form\"]/span"));

		closeModal.click();

		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"output\"]/div[1]")));

		WebElement topCard = this.driver.findElement(By.xpath("//*[@id=\"output\"]/div[1]"));

		Assertions.assertTrue(topCard.getText().contains("Java for a dummy"));

	}

	@Test
	void seleniumDeleteTest() {
		this.driver.get("http://127.0.0.1:5500/");

		WebElement deleteButton = this.driver.findElement(By.xpath("//*[@id=\"output\"]/div[1]/div[2]/button[2]"));

		deleteButton.click();

		WebElement output = this.driver.findElement(By.xpath("/html"));

		Assertions.assertTrue(output.getText().contains("Book Deleted"));
	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}

}