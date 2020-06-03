package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.testClasses.TestHireController;
import test.testClasses.TestLoginController;

@RunWith(Suite.class)
@SuiteClasses({ TestHireController.class, TestLoginController.class })
public class LoginAndHireTests {

}
