package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().contacts().size() == 0) {
            Groups groups = app.db().groups();
            File photo = new File("src/test/resources/goldie.jpg");
            app.goTo().goToHomePage();
            ContactData newContact = new ContactData().withFirstname("Goldie").withMiddlename("Jeanne").withLastname("Hawn")
                    .withAddress("Hollywood").withHomePhone("555555").withMobilePhone("+3421").withWorkPhone("223 444")
                    .withEmail("goldie@12.ru").inGroup(groups.iterator().next()).withPhoto(photo);
            app.getContactHelper().createContact(newContact, true);
        }
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        Contacts before = app.db().contacts();

        ContactData contactToDelete = before.iterator().next();
        app.goTo().goToHomePage();
        app.getContactHelper().deleteContact(contactToDelete);

        Contacts after = app.db().contacts();

        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(contactToDelete)));
    }
}
