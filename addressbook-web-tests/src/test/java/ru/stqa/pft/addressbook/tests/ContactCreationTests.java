package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    if (! app.getGroupHelper().isThereTestGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test4", "test5", "test6"));
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().createContact(new ContactData("Goldie", "Jeanne", "Hawn", "Hollywood", "555555", "goldie@12.ru", "test4"), true);
  }
}
