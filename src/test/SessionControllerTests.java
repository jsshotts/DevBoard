package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.classes.SessionDevAndPOTests;
import test.classes.SessionSingletonAndUpdateTests;

@RunWith(Suite.class)
@SuiteClasses({ SessionDevAndPOTests.class, SessionSingletonAndUpdateTests.class })
public class SessionControllerTests {

}
