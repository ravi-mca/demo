package com.favendo.merchant.service.helper;

import com.favendo.commons.utils.UniqueIdGenerator;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.user.service.domain.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Listeners(MockitoTestNGListener.class)
public class MerchantHelperTest {

    @InjectMocks
    private MerchantHelper subject;

    @Mock
    private UniqueIdGenerator uniqueIdGenerator;

    @Test
    public void buildMerchant_WithAllInformations_BuildAndReturnsMerchant() {
        String phone = "9898102525";
        String firstName = "Storecast";
        String lastName = "Storecast";
        String email = "storecast@gmail.com";
        String accountNo = "ABCD-EFGH-IJCK-LMOP";
        String accountName = "storecast_account_name";

        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setFirstName(firstName);
        merchantDto.setLastName(lastName);
        merchantDto.setEmail(email);
        merchantDto.setPhone(phone);
        merchantDto.setAccountNo(accountNo);
        merchantDto.setAccountName(accountName);

        User result = subject.buildMerchant(merchantDto);
        User expected = createMerchant(firstName, lastName, accountNo, accountName, phone, email);

        assertThat(result.getFirstName(), is(expected.getFirstName()));
        assertThat(result.getLastName(), is(expected.getLastName()));
        assertThat(result.getUsername(), is(expected.getUsername()));
        assertThat(result.getPhone(), is(expected.getPhone()));
        assertThat(result.getAccountName(), is(expected.getAccountName()));
    }

    private User createMerchant(String firstName, String lastName, String accountNo, String accountName,
                                String phone, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(email);
        user.setPhone(phone);
        user.setAccountNo(accountNo);
        user.setAccountName(accountName);
        return user;
    }
}
