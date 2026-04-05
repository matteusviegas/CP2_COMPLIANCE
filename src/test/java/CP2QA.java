import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class CP2QA {
    //login sucesso
    @Test
    public void loginTeste() {
        //Dado: que esteja na  página saucedemo.com
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        assert driver.getCurrentUrl().equals("https://www.saucedemo.com/");
        assert driver.getTitle().equals("Swag Labs");

        //Quando: inserido dados de usuário e senha válidos
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Então: devera ser redirecionado para a pgina https://www.saucedemo.com/inventory.html
        assert driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
        assert driver.findElement(By.id("shopping_cart_container")).isDisplayed();
    }
    @Test
    public void SenhaIncorreta() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("senhaerrada");
        driver.findElement(By.id("login-button")).click();

        String erro = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        assertTrue(erro.contains("Username and password do not match"));
    }

    @Test
    public void EntrarSemUsuario() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String erro = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        assertTrue(erro.contains("Username is required"));


    }

    @Test
    public void loginCamposVazios() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("login-button")).click();

        String erro = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        assertTrue(erro.contains("Username is required"));

    }
}
