package edu.colegiosprisma.school;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {

    public void loginGo(WebDriver driver) {

        driver.get("http://localhost:8080/");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement txtUsername = driver.findElement(By.id("username"));
        txtUsername.sendKeys("P020220001");

        WebElement txtPassword = driver.findElement(By.id("password"));
        txtPassword.sendKeys("78017132");

        driver.findElement(By.id("submit")).click();
    }

    @Test
    public void Login() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Desktop\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        loginGo(driver);

        String titulo = driver.getTitle();
        String titulo2 = "Padres - Inicio";
        Assert.assertEquals(titulo, titulo2);
    }

    @Test
    public void ResgisterAlumno() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        loginGo(driver);
        driver.get("http://localhost:8080/parent/applicants-form");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement givenNames = driver.findElement(By.id("given-names"));
        givenNames.sendKeys("Luis");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement firstLastName = driver.findElement(By.id("first-last-name"));
        firstLastName.sendKeys("Burns");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement secondLastName = driver.findElement(By.id("second-last-name"));
        secondLastName.sendKeys("Claus");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement gender = driver.findElement(By.id("gender"));
        gender.sendKeys("Masculino");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement documentType = driver.findElement(By.id("document-type"));
        documentType.sendKeys("DNI");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement documentNumber = driver.findElement(By.id("document-number"));
        documentNumber.sendKeys("45684712");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement birthDate = driver.findElement(By.id("birth-date"));
        birthDate.sendKeys("06/06/2005");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement nationality = driver.findElement(By.id("nationality"));
        nationality.sendKeys("Peruana");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement relationship = driver.findElement(By.id("relationship"));
        relationship.sendKeys("Padre");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement level = driver.findElement(By.id("level"));
        level.sendKeys("Secundaria");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement grade = driver.findElement(By.id("grade"));
        grade.sendKeys("5° Secundaria");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement address = driver.findElement(By.id("address"));
        address.sendKeys("Polo Norte - norte123");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        driver.findElement(By.id("submit")).click();

    }

    @Test
    public void RealizarPago() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        loginGo(driver);
        driver.get("http://localhost:8080/parent/applicants-list");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.className("realizarPago")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement fullNames = driver.findElement(By.id("full-names"));
        fullNames.sendKeys("Percy Sanchez");

        WebElement cardNumber = driver.findElement(By.id("card-number"));
        cardNumber.sendKeys("1234-1234-1234-1234");

        WebElement month = driver.findElement(By.id("month"));
        month.sendKeys("10");

        WebElement year = driver.findElement(By.id("year"));
        year.sendKeys("25");

        WebElement cvv = driver.findElement(By.id("cvv"));
        cvv.sendKeys("123");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.findElement(By.className("pagar")).click();

    }

    @Test
    public void ActualizaUsuario() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        loginGo(driver);
        driver.get("http://localhost:8080/parent/parent-profile");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement givenNames = driver.findElement(By.name("givenNames"));
        givenNames.clear();
        givenNames.sendKeys("Percydsad");

        WebElement firstLastName = driver.findElement(By.name("firstLastName"));
        firstLastName.clear();
        firstLastName.sendKeys("Sanchez");

        WebElement secondLastName = driver.findElement(By.name("secondLastName"));
        secondLastName.clear();
        secondLastName.sendKeys("Chambi");

        WebElement email = driver.findElement(By.name("email"));
        email.clear();
        email.sendKeys("percys@outlook.com");

        WebElement phone = driver.findElement(By.name("phone"));
        phone.clear();
        phone.sendKeys("999007458");

        WebElement address = driver.findElement(By.name("address"));
        address.clear();
        address.sendKeys("mz i");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        driver.findElement(By.id("submit")).click();


    }

    @Test
    public void crearCuenta() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/register");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement givenNames = driver.findElement(By.id("given-names"));
        givenNames.sendKeys("Manuela");

        WebElement firstLastName = driver.findElement(By.id("first-last-name"));
        firstLastName.sendKeys("Santa");

        WebElement secondLastName = driver.findElement(By.id("second-last-name"));
        secondLastName.sendKeys("Claus");

        WebElement documentType = driver.findElement(By.id("document-type"));
        documentType.sendKeys("DNI");

        WebElement documentNumber = driver.findElement(By.id("document-number"));
        documentNumber.sendKeys("14754215");

        WebElement birthDate = driver.findElement(By.id("birth-date"));
        birthDate.sendKeys("06/06/1978");

        WebElement address = driver.findElement(By.id("address"));
        address.sendKeys("mz i");

        WebElement gender = driver.findElement(By.id("gender"));
        gender.sendKeys("Femenimo");

        WebElement nationality = driver.findElement(By.id("nationality"));
        nationality.sendKeys("Peruana");

        WebElement phone = driver.findElement(By.id("phone-number"));
        phone.sendKeys("923456789");

        WebElement email = driver.findElement(By.id("email-address"));
        email.sendKeys("zus_1997@outlook.com");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        driver.findElement(By.id("submit")).click();


    }


}