package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.classes.DatabaseGetAll;
import test.classes.DatabaseGetOne;

@RunWith(Suite.class)
@SuiteClasses({ DatabaseGetAll.class, DatabaseGetOne.class })
public class DatabaseTests {

}