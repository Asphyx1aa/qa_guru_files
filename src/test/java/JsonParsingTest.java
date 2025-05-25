import com.fasterxml.jackson.databind.ObjectMapper;
import model.PersonFriendsModel;
import model.PersonModel;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParsingTest {

    @Test
    void parsePersonJson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("person.json")) {
            assert is != null;
            PersonModel personModel = objectMapper.readValue(is, PersonModel.class);

            assertNotNull(personModel);
            assertEquals("Chris", personModel.getName());
            assertEquals(23, personModel.getAge());
            assertNotNull(personModel.getAddress());
            assertEquals("New York", personModel.getAddress().getCity());
            assertEquals("America", personModel.getAddress().getCountry());

            List<PersonFriendsModel> friends = personModel.getFriends();
            assertNotNull(friends);
            assertEquals(2, friends.size());
        }
    }
}