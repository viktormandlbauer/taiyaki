package at.technikum.taiyaki.backend.dto.profile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class ProfileDto {
    private String firstName;
    private String lastName;
    private String salutation;
    private String username;
    private String email;
    private String street;
    private String city;
    private String postalCode;
    private String country;
}
