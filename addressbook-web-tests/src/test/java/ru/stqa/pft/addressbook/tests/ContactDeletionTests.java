package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Goldie", "Jeanne", "Hawn", "Hollywood", "555555", "goldie@12.ru", "test4"), true);
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().goToHomePage();

        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
