package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void  ensurePrecondition() {
    if (! app.group().isThereTestGroup()) {
      app.group().create(new GroupData().withName("test4").withHeader("test5").withFooter("test6"));
    }
    app.goTo().goToHomePage();
  }

  @Test(enabled = true)
  public void testContactCreation() {
    Set<ContactData> before = app.getContactHelper().all();
    ContactData contact = new ContactData().withFirstname("Goldie").withMiddlename("Jeanne").withLastname("Hawn")
            .withAddress("Hollywood").withHomephone("555555").withEmail("goldie@12.ru").withGroup("test4");
    app.getContactHelper().createContact(contact, true);
    Set<ContactData> after = app.getContactHelper().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
