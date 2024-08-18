package com.metis.book;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddressServiceTest.class,
        CartItemServiceTest.class,
        BookServiceTest.class,
        LanguageServiceTest.class,
        OrderServiceImplTest.class,
        OrderTrackServiceImplTest.class,
        PasswordResetTokenServiceImplTest.class,
        UserServiceImplTest.class,
        VerificationTokenServiceImplTest.class,
        AuthorServiceImplTest.class,
        BookRequestImplTest.class,
        ContactServiceImplTest.class,
        FeedbackServiceImplTest.class,
        AimServiceImplTest.class,
        CartServiceImplTest.class,
        CategoryServiceImplTest.class,
        CustomUserServiceImplTest.class,

})
public class TestSuite {
}
