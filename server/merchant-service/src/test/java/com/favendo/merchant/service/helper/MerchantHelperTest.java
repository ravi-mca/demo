package com.favendo.merchant.service.helper;

import com.favendo.commons.utils.UniqueIdGenerator;
import com.favendo.merchant.service.dto.MerchantDto;
import com.favendo.user.service.domain.Role;
import com.favendo.user.service.domain.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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

        Long roleId = 2L;
        String roleName = "ROLE_MERCHANT";
        Role role = createRole(roleId, roleName);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setFirstName(firstName);
        merchantDto.setLastName(lastName);
        merchantDto.setEmail(email);
        merchantDto.setPhone(phone);
        merchantDto.setAccountNo(accountNo);
        merchantDto.setAccountName(accountName);

        User result = subject.buildMerchant(merchantDto, role);
        User expected = createMerchant(firstName, lastName, accountNo, accountName, phone, email, roles);

        assertThat(result.getFirstName(), is(expected.getFirstName()));
        assertThat(result.getLastName(), is(expected.getLastName()));
        assertThat(result.getUsername(), is(expected.getUsername()));
        assertThat(result.getPhone(), is(expected.getPhone()));
        assertThat(result.getAccountName(), is(expected.getAccountName()));
        assertThat(result.getRoles(), is(expected.getRoles()));
    }

    private User createMerchant(String firstName, String lastName, String accountNo, String accountName,
                                String phone, String email, List<Role> roles) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(email);
        user.setPhone(phone);
        user.setAccountNo(accountNo);
        user.setAccountName(accountName);
        user.setRoles(roles);
        return user;
    }

    private Role createRole(Long roleId, String roleName) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        return role;
    }
}
