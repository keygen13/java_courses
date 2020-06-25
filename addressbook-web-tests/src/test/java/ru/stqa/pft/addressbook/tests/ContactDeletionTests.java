package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData().withFirstname("Goldie").withMiddlename("Jeanne").withLastname("Hawn")
                    .withAddress("Hollywood").withHomephone("555555").withEmail("goldie@12.ru").withGroup("test4"), true);
        }
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        Contacts before = app.getContactHelper().all();

        ContactData contactToDelete = before.iterator().next();
        app.getContactHelper().deleteContact(contactToDelete);
        app.goTo().goToHomePage();

        Contacts after = app.getContactHelper().all();

        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(contactToDelete)));
    }
}
