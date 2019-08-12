package utils;

import dao.DaoUsersImpl;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChecksUtilTest {

    @Test
    public void lengthCheck() {
        final String[] strings0 = new String[0];
        final boolean actual0 = ChecksUtil.lengthCheck(strings0);
        Assert.assertEquals(false, actual0);
        final String[] strings2 = new String[2];
        final boolean actual2 = ChecksUtil.lengthCheck(strings2);
        Assert.assertEquals(true, actual2);
        final String[] strings4 = new String[4];
        final boolean actual4 = ChecksUtil.lengthCheck(strings4);
        Assert.assertEquals(false, actual4);
    }

    @Test
    public void phonesValidation() {
        String phone1 = "37500 1234567";
        String phone2 = "37700 1234567";
        String phone3 = "375001234567";
        String phone4 = "375001 1234567";
        String phone5 = "37500 12345678";
        String phone6 = "375aa 1234567";
        String phone7 = "37500 bb34567";
        Assert.assertEquals(true, ChecksUtil.phonesValidation(new String[]{phone1}));
        Assert.assertEquals(false, ChecksUtil.phonesValidation(new String[]{phone2}));
        Assert.assertEquals(false, ChecksUtil.phonesValidation(new String[]{phone3}));
        Assert.assertEquals(false, ChecksUtil.phonesValidation(new String[]{phone4}));
        Assert.assertEquals(false, ChecksUtil.phonesValidation(new String[]{phone5}));
        Assert.assertEquals(false, ChecksUtil.phonesValidation(new String[]{phone6}));
        Assert.assertEquals(false, ChecksUtil.phonesValidation(new String[]{phone7}));
    }

    @Test
    public void emailValidation() {
        String email0 = "akvolkov@mail.ru";
        String email1 = "akvolkovmail.ru";
        String email2 = "akvolkov@mailru";
        Assert.assertEquals(true, ChecksUtil.emailValidation(email0));
        Assert.assertEquals(false, ChecksUtil.emailValidation(email1));
        Assert.assertEquals(false, ChecksUtil.emailValidation(email2));
    }

    @Test
    public void emailExistence() {
        final DaoUsersImpl daoUsers = new DaoUsersImpl();
        daoUsers.addUser("asd", "asfsa", "akv@mail.ru", new String[]{"dvsvd"}, new String[]{"37500 1234567"});
        Assert.assertEquals(true, ChecksUtil.emailExistence("akv@mail.ru"));
        Assert.assertEquals(false, ChecksUtil.emailExistence("dsvsdbssfsdsdcascascas@bsdbsdbsdb.sdbvsdbsd"));
        daoUsers.remove("akv@mail.ru");
    }
}