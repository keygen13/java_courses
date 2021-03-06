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

public class AddingContactToGroup extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        System.out.println("number of groups: " + app.db().groups().size());
        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test4"));
        }
        Contacts contacts = app.db().contacts();
        ContactData testContact = null;
        for(ContactData contact : contacts) {
            if(contact.getGroups().size() < app.db().groups().size()) {
                return;
            }
        }
            File photo = new File("src/test/resources/goldie.jpg");
            testContact = new ContactData().withFirstname("Goldie").withMiddlename("Jeanne").withLastname("Hawn")
                    .withAddress("Hollywood").withHomePhone("555555").withMobilePhone("+3421").withWorkPhone("223 444")
                    .withEmail("goldie@12.ru").withPhoto(photo);
            app.getContactHelper().createContact(testContact, true);
    }

    @Test
    public void testAddingToGroup() {
        ContactData testContact = null;
        Contacts contacts = app.db().contacts();
        for(ContactData contact : contacts) {
            if(contact.getGroups().size() < app.db().groups().size()) {
                testContact = contact;
                break;
            }
        }
        GroupData testGroup = null;
        Groups testContactGroups = testContact.getGroups();
        Groups groups = app.db().groups();
        for(GroupData group : groups) {
            if(!testContactGroups.contains(group)) {
               testGroup = group;
               break;
            }
        }
        System.out.println(testContact);
        System.out.println(testGroup);

        app.goTo().goToHomePage();
        app.getContactHelper().selectContactById(testContact.getId());
        app.getContactHelper().addSelectedContactToGroup(testGroup);

        Contacts contacts2 = app.db().contacts();

        int testContactId = testContact.getId();

        ContactData testContactModified = contacts2
                .stream()
                .filter((s) -> s.getId() == testContactId)
                .collect(Collectors.toList())
                .get(0);

        Assert.assertTrue(testContactModified.getGroups().contains(testGroup));

    }

}
