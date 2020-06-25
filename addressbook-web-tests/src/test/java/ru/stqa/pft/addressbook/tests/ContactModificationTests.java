package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData().withFirstname("Goldie").withMiddlename("Jeanne")
                    .withLastname("Hawn").withAddress("Hollywood").withHomephone("555555").withEmail("goldie@12.ru")
                    .withGroup("test4"), true);
        }
    }

    @Test(enabled = true)
    public void testContactModification() {

        Contacts before = app.getContactHelper().all();

        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData().withId(contactToModify.getId()).withFirstname("Aloy")
                .withMiddlename("").withLastname("Sobeck").withAddress("Nora").withEmail("nora.cave@12.ru");

        app.getContactHelper().initContactModification(contactToModify);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.goTo().goToHomePage();

        Contacts after = app.getContactHelper().all();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
    }
}
