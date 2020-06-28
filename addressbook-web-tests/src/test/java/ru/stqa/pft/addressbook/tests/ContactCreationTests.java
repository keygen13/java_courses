package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (! app.group().isThereTestGroup()) {
      app.group().create(new GroupData().withName("test4").withHeader("test5").withFooter("test6"));
    }
    app.goTo().goToHomePage();
  }

  @Test(enabled = true)
  public void testContactCreation() {
    Contacts before = app.getContactHelper().all();
    ContactData contact = new ContactData().withFirstname("Goldie").withMiddlename("Jeanne").withLastname("Hawn")
            .withAddress("Hollywood").withHomePhone("5555-55").withMobilePhone("+3421").withWorkPhone("223 444")
            .withEmail("goldie@12.ru").withGroup("test4");
    app.getContactHelper().createContact(contact, true);
    Set<ContactData> after = app.getContactHelper().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
