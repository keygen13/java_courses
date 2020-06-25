package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

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
        Set<ContactData> before = app.getContactHelper().all();
        ContactData contactToDelete = before.iterator().next();
        app.getContactHelper().selectContactById(contactToDelete.getId());
        app.getContactHelper().deleteSelectedContacts();
        app.goTo().goToHomePage();

        Set<ContactData> after = app.getContactHelper().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(contactToDelete);
        Assert.assertEquals(before, after);
    }
}
