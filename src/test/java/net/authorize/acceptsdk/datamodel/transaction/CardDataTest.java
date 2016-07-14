package net.authorize.acceptsdk.datamodel.transaction;

import junit.framework.Assert;
import net.authorize.acceptsdk.exception.AcceptInvalidCardException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Kiran Bollepalli on 14,July,2016.
 * kbollepa@visa.com
 */
public class CardDataTest {

  private String cardNumber = "4111111111111111";
  private String month = "11";
  private String year = "2020";

  @Before public void setUp() throws Exception {

  }

  @Test public void testCardNumber() throws Exception {
    cardNumber = "4111111111111111";
    month = "11";
    year = "2020";
    CardData card = new CardData.Builder(cardNumber, month, year).build();
    Assert.assertNotNull(card);
  }

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Test public void testCardNumberForNull() throws AcceptInvalidCardException {
    cardNumber = null;
    month = "11";
    year = "2020";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_NUMBER);
    CardData card = new CardData.Builder(cardNumber, month, year).build();
  }

  @Test public void testCardNumberMinimumLength() throws AcceptInvalidCardException {
    cardNumber = "4111";
    month = "11";
    year = "2020";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_NUMBER);
    CardData card = new CardData.Builder(cardNumber, month, year).build();
  }

  @Test public void testCardNumberMaximumLength() throws AcceptInvalidCardException {
    cardNumber = "411111111111111111111111111111111111111";
    month = "11";
    year = "2020";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_NUMBER);
    CardData card = new CardData.Builder(cardNumber, month, year).build();
  }

  @Test public void testCardNumberForChars() throws AcceptInvalidCardException {
    cardNumber = "41111AAAAA";
    month = "11";
    year = "2020";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_NUMBER);
    CardData card = new CardData.Builder(cardNumber, month, year).build();
  }

  @Test public void testExpirationMonth() throws AcceptInvalidCardException {
    cardNumber = "4111111111111111";
    month = "aa";
    year = "2020";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_EXPIRATION_MONTH);
    CardData card = new CardData.Builder(cardNumber, month, year).build();
  }
}