package main.third.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomResponse<T> {
    private int status;
    private List<? extends T> body;
}
