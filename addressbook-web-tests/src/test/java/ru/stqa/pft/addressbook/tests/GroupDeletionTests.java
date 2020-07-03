package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void  ensurePrecondition() {
      if(app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test4"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    GroupData groupForDeletion = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(groupForDeletion);

    assertThat(app.group().count(), equalTo(before.size()-1));
    Groups after = app.db().groups();

    assertThat(after, equalTo(before.without(groupForDeletion)));
    }

}
