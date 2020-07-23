package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper (ApplicationManager app) {
        this.app = app;
        this.wiser = new Wiser();
    }

    public List<MailMessage> waitForMail(int count, long timeout) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout) {
            if(wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail");
    }

    public static MailMessage toModelMail(WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteAllMail(long timeout) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout) {
           // List<WiserMessage> messages = wiser.getMessages();
            int messageCount = wiser.getMessages().size();
            WiserMessage message;
            for(int i = 0; i < messageCount; i++) {
                //wiser.getMessages().removeAll()
                //message = wiser.getMessages().get(0);
                wiser.getMessages().remove(0);
            }
            messageCount = wiser.getMessages().size();
            messageCount = wiser.getMessages().size();
        }
    }

    public void start() {wiser.start();}

    public void stop() {wiser.stop();}
}
