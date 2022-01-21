package pl.kk.gorest.registration;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Data {

    String name;
    String email;
    String gender;
    String status;
}
