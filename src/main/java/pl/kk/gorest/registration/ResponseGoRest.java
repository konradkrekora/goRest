package pl.kk.gorest.registration;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseGoRest {
    private boolean meta;
    private Data data;


}
