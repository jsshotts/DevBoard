package test;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import controller.DatabaseController;

public class UtilityTest {

	@Test
	public void test() {
		DatabaseController database = new DatabaseController();
		String temp = "cf1910cf-485f-449f-b4f5-5ec08d889eac";
		UUID uid = UUID.fromString(temp);
		database.deleteOffer(uid);
	}
}