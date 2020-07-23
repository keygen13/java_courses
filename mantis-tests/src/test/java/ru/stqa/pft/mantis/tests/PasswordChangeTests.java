package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class PasswordChangeTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void changePasswordTest() throws IOException {
        String[] testUserData = app.pass().getTestUser();
        String user = testUserData[0];
        String email = testUserData[1];
        String changedPassword = "drowssap";

        app.pass().start();
        app.pass().sendLinkForPassReset(user);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 20000);
        String passConfirmationLink = findPassConfirmationLink(mailMessages, email);
        app.pass().finish(passConfirmationLink, changedPassword);
        assertTrue(app.newSession().login(user, changedPassword));
    }

    private String findPassConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email))
                .filter((m) -> m.text.contains("requested a password change")).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
