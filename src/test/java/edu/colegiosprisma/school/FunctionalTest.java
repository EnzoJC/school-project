package edu.colegiosprisma.school;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {
    @Test
    public void Login() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\alexa\\Desktop\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:8080/");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement txtUsername = driver.findElement(By.id("username"));
        txtUsername.sendKeys("P020220001");

        WebElement txtPassword = driver.findElement(By.id("password"));
        txtPassword.sendKeys("78017132");

        driver.findElement(By.id("submit")).click();



    }
}
