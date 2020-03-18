package br.com.senior.core.user;

import br.com.senior.core.BaseIT;
import br.com.senior.core.authentication.AuthenticationClient;
import br.com.senior.core.authentication.pojos.LoginInput;
import br.com.senior.core.user.pojos.CreateGroupInput;
import br.com.senior.core.user.pojos.CreateGroupOutput;
import br.com.senior.core.user.pojos.CreateUserInput;
import br.com.senior.core.user.pojos.CreateUserOutput;
import br.com.senior.core.user.pojos.GetGroupInput;
import br.com.senior.core.user.pojos.GetGroupOutput;
import br.com.senior.core.user.pojos.GetUserInput;
import br.com.senior.core.user.pojos.GetUserOutput;
import br.com.senior.core.user.pojos.ListGroupUsersInput;
import br.com.senior.core.user.pojos.ListGroupUsersOutput;
import br.com.senior.core.user.pojos.ListGroupsInput;
import br.com.senior.core.user.pojos.ListGroupsOutput;
import br.com.senior.core.user.pojos.UpdateGroupInput;
import br.com.senior.core.user.pojos.UpdateGroupOutput;
import br.com.senior.core.user.pojos.UpdateGroupUsersInput;
import br.com.senior.core.user.pojos.UpdateGroupUsersOutput;
import br.com.senior.core.user.pojos.UpdateUserInput;
import br.com.senior.core.user.pojos.UpdateUserOutput;
import br.com.senior.core.utils.ServiceException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Exemplos de código do {@link br.com.senior.core.user.UserClient UserClient}
 */
public class UserIT extends BaseIT {

    private static final String USER_NAME = "UsuarioParaTestes";

    private static final String GROUP_ID = UUID.randomUUID().toString();
    private static final String GROUP_NAME = "GrupoParaTestes";

    private static String usernameExpected;
    private static String token;
    private static UserClient client;

    @BeforeClass
    public static void beforeClass() throws ServiceException {
        usernameExpected = Arrays.stream(System.getenv("USERNAME").split("@")).findFirst().orElse(null);
        token = new AuthenticationClient().login(new LoginInput(System.getenv("username"), System.getenv("password_valid"))).getJsonToken().getAccess_token();
        client = new UserClient(token);
    }

    @Test
    public void testCreateAndGetGroup() throws ServiceException {
        CreateGroupOutput createGroupOutput = createGroup();
        Assert.assertNotNull(createGroupOutput);

        GetGroupOutput getGroupOutput = getGroup();
        Assert.assertNotNull(getGroupOutput);
    }

    @Test
    public void testCreateAndListGroups() throws ServiceException {
        CreateUserOutput createUserOutput = createUser();
        Assert.assertNotNull(createUserOutput);

        CreateGroupOutput createGroupOutput = createGroup();
        Assert.assertNotNull(createGroupOutput);

        ListGroupsOutput listGroupsOutput = listGroups();
        Assert.assertNotNull(listGroupsOutput);
        Assert.assertEquals(1, listGroupsOutput.groups.size());
    }

    @Test
    public void testCreateAndListGroupUsers() throws ServiceException {
        CreateUserOutput createUserOutput = createUser();
        Assert.assertNotNull(createUserOutput);

        CreateGroupOutput createGroupOutput = createGroup();
        Assert.assertNotNull(createGroupOutput);

        ListGroupUsersOutput listGroupUsersOutput = listGroupUsers();
        Assert.assertNotNull(listGroupUsersOutput);
        Assert.assertEquals(1, listGroupUsersOutput.users.size());
    }

    @Test
    public void testGetAndUpdateGroup() throws ServiceException {
        GetGroupOutput getGroupOutput = getGroup();
        Assert.assertNotNull(getGroupOutput);

        UpdateGroupOutput updateGroupOutput = updateGroup();
        Assert.assertNotNull(updateGroupOutput);
    }

    @Test
    public void testGetAndUpdateGroupUsers() throws ServiceException {
        GetGroupOutput getGroupOutput = getGroup();
        Assert.assertNotNull(getGroupOutput);

        GetUserOutput getUserOutput = getUser();
        Assert.assertNotNull(getUserOutput);

        UpdateGroupUsersOutput updateGroupUsersOutput = updateGroupUsers();
        Assert.assertNotNull(updateGroupUsersOutput);
    }

    @Test
    public void testCreateAndGetUser() throws ServiceException {
        CreateUserOutput createUserOutput = createUser();
        Assert.assertNotNull(createUserOutput);

        GetUserOutput getUserOutput = getUser();
        Assert.assertNotNull(getUserOutput);
    }

    @Test
    public void testGetAndUpdateUser() throws ServiceException {
        GetUserOutput getUserOutput = getUser();
        Assert.assertNotNull(getUserOutput);

        UpdateUserOutput updateUserOutput = updateUser();
        Assert.assertNotNull(updateUserOutput);
    }

    private GetGroupOutput getGroup() throws ServiceException {
        GetGroupInput input = new GetGroupInput(GROUP_ID);
        return client.getGroup(input);
    }

    private CreateGroupOutput createGroup() throws ServiceException {
        CreateGroupInput input = CreateGroupInput.builder()
                .name(GROUP_NAME)
                .description("Descricao do Grupo")
                .email("email@group.com")
//                .users(List.of(USER_NAME))
                .build();

        return client.createGroup(input);
    }

    private UpdateGroupOutput updateGroup() throws ServiceException {
        UpdateGroupInput input = UpdateGroupInput.builder()
                .name(GROUP_NAME)
                .description("Descricao do Grupo")
                .email("email@group.com")
//                .usersToRemove(List.of(USER_NAME))
                .build();

        return client.updateGroup(input);
    }

    private ListGroupsOutput listGroups() throws ServiceException {
        ListGroupsInput input = new ListGroupsInput();
        return client.listGroups(input);
    }

    private ListGroupUsersOutput listGroupUsers() throws ServiceException {
        ListGroupUsersInput input = new ListGroupUsersInput(GROUP_ID);
        return client.listGroupUsers(input);
    }

    private GetUserOutput getUser() throws ServiceException {
        GetUserInput input = new GetUserInput(USER_NAME);
        return client.getUser(input);
    }

    private CreateUserOutput createUser() throws ServiceException {
        CreateUserInput input = CreateUserInput.builder()
                .username(USER_NAME)
                .fullName("nome completo")
                .password("SuaSenha123")
                .changePassword(false)
                .blocked(false)
                .build();

        return client.createUser(input);
    }

    private UpdateUserOutput updateUser() throws ServiceException {
        UpdateUserInput input = UpdateUserInput.builder()
                .username(USER_NAME)
                .fullName("nome completo")
                .changePassword(false)
                .blocked(false)
                .build();

        return client.updateUser(input);
    }

    private UpdateGroupUsersOutput updateGroupUsers() throws ServiceException {
        UpdateGroupUsersInput input = UpdateGroupUsersInput.builder()
                .groupId(GROUP_ID)
//                .usersToRemove(List.of())
//                .usersToAdd(List.of(USER_NAME))
                .build();

        return client.updateGroupUsers(input);
    }
}