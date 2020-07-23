package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.sql.*;

public class PassHelper extends HelperBase {

    public PassHelper(ApplicationManager app) {
        super(app);
    }

    public void start() {
        wd.get(app.getProperty("web.baseURL"));
        type(By.name("username"), app.getProperty("web.adminLogin"));
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[type='submit']"));
        click(By.cssSelector("a[href='/mantisbt-2.24.1/manage_overview_page.php']"));
        click(By.xpath("//div[@id='main-container']/div[2]/div[2]/div/ul/li[2]/a"));
    }

    public void sendLinkForPassReset(String username) {
        click(By.linkText(username));
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public String[] getTestUser() {
        String[] user = new String[2];
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(app.getProperty("bd.url"));
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select username, email from mantis_user_table where username <> 'administrator'");
            while (rs.next()) {
                user[0] = rs.getString("username");
                user[1] = rs.getString("email");
                break;
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return user;
    }

    public void finish(String passConfirmationLink, String changedPassword) {
        wd.get(passConfirmationLink);
        type(By.name("password"), changedPassword);
        type(By.name("password_confirm"), changedPassword);
        click(By.cssSelector("button[type='submit']"));
    }
}
