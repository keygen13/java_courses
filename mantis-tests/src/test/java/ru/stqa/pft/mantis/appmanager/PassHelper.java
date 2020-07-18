package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class PassHelper extends HelperBase {

    public PassHelper(ApplicationManager app) {
        super(app);
    }

    public void start() {
        wd.get(app.getProperty("web.baseURL"));
        type(By.name("username"), app.getProperty("web.adminLogin"));
        click(By.cssSelector("input[type='submit']"));
        //type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[type='submit']"));
        //click(By.xpath("//div[@id='navbar-container']/div[2]/ul/li[3]/a/span"));
        click(By.cssSelector("a[href='/mantisbt-2.24.1/manage_overview_page.php']"));
        click(By.xpath("//div[@id='main-container']/div[2]/div[2]/div/ul/li[2]/a"));
    }

    public void sendLinkForPassReset(String username) {
        click(By.linkText(username));
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void finish(String passConfirmationLink, String changedPassword) {
        wd.get(passConfirmationLink);
        type(By.name("password"), changedPassword);
        type(By.name("password_confirm"), changedPassword);
        click(By.cssSelector("button[type='submit']"));
    }
}
