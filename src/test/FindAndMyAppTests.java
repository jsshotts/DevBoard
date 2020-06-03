package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.testClasses.TestFindProjectsController;
import test.testClasses.TestMyAppsController;

@RunWith(Suite.class)
@SuiteClasses({ TestFindProjectsController.class, TestMyAppsController.class })
public class FindAndMyAppTests {

}
