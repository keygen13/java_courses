package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.stream.Collectors;

import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class RemoveContactFromGroup {

    @BeforeMethod
    public void ensurePrecondition() {
        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test4"));
        }
        Contacts contacts = app.db().contacts();
        ContactData testContact = null;
        for(ContactData contact : contacts) {
            if(contact.getGroups().size() > 0) {
                return;
            }
        }
        File photo = new File("src/test/resources/goldie.jpg");
        testContact = new ContactData().withFirstname("Goldie").withMiddlename("Jeanne").withLastname("Hawn")
                .withAddress("Hollywood").withHomePhone("555555").withMobilePhone("+3421").withWorkPhone("223 444")
                .withEmail("goldie@12.ru").withPhoto(photo).inGroup(app.db().groups().iterator().next());
        app.getContactHelper().createContact(testContact, true);
    }

    @Test
    public void testRemovingFromGroup() {
        ContactData testContact = null;
        Contacts contacts = app.db().contacts();
        for(ContactData contact : contacts) {
            if(contact.getGroups().size() > 0) {
                testContact = contact;
                break;
            }
        }
        GroupData testGroup = testContact.getGroups().iterator().next();

        System.out.println(testContact);
        System.out.println(testGroup);

        app.goTo().goToHomePage();
        app.getContactHelper().selectContactById(testContact.getId());
        app.getContactHelper().addSelectedContactToGroup(testGroup);

        Contacts contacts2 = app.db().contacts();

        int testContactId = testContact.getId();

        ContactData testContact2 = contacts2
                .stream()
                .filter((s) -> s.getId() == testContactId)
                .collect(Collectors.toList())
                .get(0);

        Assert.assertTrue(testContact2.getGroups().contains(testGroup));

    }
}
