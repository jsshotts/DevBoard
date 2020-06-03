package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.classes.TestHireController;
import test.classes.TestLoginController;

@RunWith(Suite.class)
@SuiteClasses({ TestHireController.class, TestLoginController.class })
public class LoginAndHireTests {

}
