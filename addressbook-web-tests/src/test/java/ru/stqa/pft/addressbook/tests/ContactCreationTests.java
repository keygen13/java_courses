package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    if (! app.getGroupHelper().isThereTestGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test4", "test5", "test6"));
    }
    app.getNavigationHelper().goToHomePage();

    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().createContact(new ContactData("Goldie", "Jeanne", "Hawn", "Hollywood", "555555", "goldie@12.ru", "test4"), true);

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
