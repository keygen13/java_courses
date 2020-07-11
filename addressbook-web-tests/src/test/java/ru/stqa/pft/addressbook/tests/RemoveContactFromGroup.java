package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.stream.Collectors;

public class RemoveContactFromGroup extends TestBase {

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
        //app.db().groups();
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
        app.getContactHelper().removeContactFromGroup(testContact, testGroup);

        Contacts contacts2 = app.db().contacts();

        int testContactId = testContact.getId();

        ContactData testContactModified = contacts2
                .stream()
                .filter((s) -> s.getId() == testContactId)
                .collect(Collectors.toList())
                .get(0);

        System.out.println("Test contact groups:");
        for(GroupData group : testContact.getGroups()) {
            System.out.println(group);
        }
        System.out.println("----------------");
        System.out.println("Modified test contact groups:");
        for(GroupData group : testContactModified.getGroups()) {
            System.out.println(group);
        }

        Assert.assertFalse(testContactModified.getGroups().contains(testGroup));

    }
}
