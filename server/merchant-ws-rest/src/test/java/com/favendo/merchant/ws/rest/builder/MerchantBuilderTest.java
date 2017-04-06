package com.favendo.merchant.ws.rest.builder;

import com.favendo.commons.utils.UniqueIdGenerator;
import com.favendo.merchant.ws.rest.dto.MerchantDto;
import com.favendo.user.service.domain.Role;
import com.favendo.user.service.domain.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Listeners(MockitoTestNGListener.class)
public class MerchantBuilderTest {
    @InjectMocks
    private MerchantBuilder subject;

    @Mock
    private UniqueIdGenerator uniqueIdGenerator;

    @Test
    public void buildMerchant_WithAllDetails_BuildAndReturnsMerchant() {
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
        assertThat(result.getRoles(), is(expected.getRoles()));
    }

    @Test
    public void buildMerchant_WithRequiredDetailsAndUser_BuildsAndReturnsMerchant() {
        Long userId = 1l;
        String phone = "9898102525";
        String firstName = "Storecast";
        String lastName = "Storecast";
        String email = "storecast@gmail.com";
        String accountNo = "ABCD-EFGH-IJCK-LMOP";
        String accountName = "storecast_account_name";

        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setFirstName(firstName);
        merchantDto.setLastName(lastName);
        merchantDto.setPhone(phone);

        User user = createMerchant(userId, firstName, lastName, accountNo, accountName, phone, email);

        User result = subject.buildMerchant(merchantDto, user);
        User expected = createMerchant(firstName, lastName, phone);

        assertThat(result.getFirstName(), is(expected.getFirstName()));
        assertThat(result.getLastName(), is(expected.getLastName()));
        assertThat(result.getPhone(), is(expected.getPhone()));
    }

    private User createMerchant(Long userId, String firstName, String lastName, String accountNo, String accountName,
                                String phone, String email) {
        User user = new User();
        user.setUser_id(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(email);
        user.setPhone(phone);
        user.setAccountNo(accountNo);
        user.setAccountName(accountName);
        return user;
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

    private User createMerchant(String firstName, String lastName, String phone) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        return user;
    }

    private Role createRole(Long roleId, String roleName) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        return role;
    }
}
