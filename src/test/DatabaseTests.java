package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.testClasses.DatabaseGetAll;
import test.testClasses.DatabaseGetOne;

@RunWith(Suite.class)
@SuiteClasses({ DatabaseGetAll.class, DatabaseGetOne.class })
public class DatabaseTests {

}