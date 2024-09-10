package main.third.dto;

import lombok.Builder;
import lombok.Data;
import main.second.enums.MethodType;

import java.util.List;

@Data
@Builder
public class ViewResponse<T> {
    private MethodType method;
    private List<? extends T> body;
}
