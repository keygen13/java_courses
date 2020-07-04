package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class AddingContactToGroup extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test4"));
        }
        Contacts contacts = app.db().contacts();
        ContactData testContact = null;
        for(ContactData contact : contacts) {
            if(contact.getGroups().size() < app.db().groups().size()) {
                testContact = contact;
                return;
            }
        }
        if(testContact == null) {
            testContact = new ContactData().withFirstname("Goldie").withMiddlename("Jeanne").withLastname("Hawn")
                    .withAddress("Hollywood").withHomePhone("555555").withMobilePhone("+3421").withWorkPhone("223 444")
                    .withEmail("goldie@12.ru");
            app.getContactHelper().createContact(testContact, true);
        }
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


        //Assert.assertTrue(testContact.getGroups().contains(testGroup));

    }

}
