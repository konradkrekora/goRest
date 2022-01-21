package pl.kk.gorest.registration;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseGoRestList {
    private Object meta;
    private List<Data> dataList;

}
